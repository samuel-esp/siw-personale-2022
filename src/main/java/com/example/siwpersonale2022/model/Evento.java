package com.example.siwpersonale2022.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descrizione;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @PositiveOrZero
    private Integer capienza;

    @NotNull
    @Positive
    private Integer prezzoInt;

    @ManyToOne
    @JoinColumn(name = "stadio_id")
    private Stadio stadio;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(mappedBy = "evento", orphanRemoval = true)
    private List<Prenotazione> prenotazioneList = new java.util.ArrayList<>();

}
