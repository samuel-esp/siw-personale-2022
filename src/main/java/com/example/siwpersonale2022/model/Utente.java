package com.example.siwpersonale2022.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Utente {

    public static final String DEFAULT_ROLE = "DEFAULT";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=6, message = "la password deve avere almeno 6 caratteri")
    private String password;

    @NotNull @Size(min=10, max =10, message = "Il numero di telefono deve avere esattamente 10 cifre")
    private String cellulare;

    @NotNull
    private String indirizzo;

    @NotNull
    private String role;

    @OneToMany(mappedBy = "utente", orphanRemoval = true)
    private List<Prenotazione> prenotazioneList = new java.util.ArrayList<>();

}
