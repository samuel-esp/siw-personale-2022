package com.example.siwpersonale2022.repository;

import com.example.siwpersonale2022.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Artista findByNomeArtistico(String nome);

    List<Artista> findByNazionalita(String nazionalita);
    
}
