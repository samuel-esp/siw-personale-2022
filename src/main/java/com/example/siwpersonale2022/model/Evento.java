package com.example.siwpersonale2022.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Integer capienza;

    private Integer prezzoInt;

    private String tipologia;

    @ManyToOne
    @JoinColumn(name = "stadio_id")
    private Stadio stadio;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(mappedBy = "evento", orphanRemoval = true)
    private List<Prenotazione> prenotazioneList = new java.util.ArrayList<>();

}
