package com.example.siwpersonale2022.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Stadio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "citta_id")
    private Citta citta;

    private String indirizzo;

    @OneToMany(mappedBy = "stadio", orphanRemoval = true)
    private List<Evento> eventoList = new java.util.ArrayList<>();


}
