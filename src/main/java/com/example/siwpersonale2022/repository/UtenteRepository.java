package com.example.siwpersonale2022.repository;

import com.example.siwpersonale2022.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findByEmail(String email);

}
