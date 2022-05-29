package com.example.siwpersonale2022.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Utente utente;

    private Evento evento;

}
