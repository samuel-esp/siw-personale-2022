package com.example.siwpersonale2022.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UtenteRegistrazioneDto {

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @Column(unique = true)
    @NotNull @Email
    private String email;

    @NotNull
    @Size(min=6)
    private String password;

    @NotNull
    @Size(min=10, max =10, message = "Il numero di telefono deve avere esattamente 10 cifre")
    private String cellulare;

    @NotNull
    private String indirizzo;

}
