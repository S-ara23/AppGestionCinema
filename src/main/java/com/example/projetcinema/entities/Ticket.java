package com.example.projetcinema.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomClient;
    private double prix;
    @Column(unique = false,nullable = true)
    private Integer codePayement;
    private boolean reservee;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;
}
