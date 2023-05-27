package com.example.projetcinema.web;

import com.example.projetcinema.dao.FilmRepository;
import com.example.projetcinema.dao.TicketRepository;
import com.example.projetcinema.entities.Film;
import com.example.projetcinema.entities.Ticket;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController

public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") long id) throws Exception{
        Film film = filmRepository.findById(id).get();
        String PhotoName = film.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + PhotoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> listTicket = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReservee(true);
            ticket.setCodePayement(ticketForm.getCodePayment());
            ticketRepository.save(ticket);
            listTicket.add(ticket);
        });
        return  listTicket;
    }

}
@Data
//@ToString
class TicketForm {
    private String nomClient;
    private int codePayment;
    private List<Long> tickets = new ArrayList<>();
}
