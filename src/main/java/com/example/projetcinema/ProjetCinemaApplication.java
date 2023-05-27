package com.example.projetcinema;

import com.example.projetcinema.entities.Film;
import com.example.projetcinema.entities.Salle;
import com.example.projetcinema.entities.Ticket;
import com.example.projetcinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Service;


@SpringBootApplication

public class ProjetCinemaApplication implements CommandLineRunner {

    @Autowired

    private ICinemaInitService CinemaInit;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {

        SpringApplication.run(ProjetCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class, Ticket.class);
        repositoryRestConfiguration.exposeIdsFor(Salle.class);
        CinemaInit.initVilles();
        CinemaInit.initCinemas();
        CinemaInit.initSalles();
        CinemaInit.initPlaces();
        CinemaInit.initSeances();
        CinemaInit.initCategories();
        CinemaInit.initFilms();
        CinemaInit.initProjections();
        CinemaInit.initTickets();

    }
}
