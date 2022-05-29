package com.example.siwpersonale2022.repository;

import com.example.siwpersonale2022.model.Artista;
import com.example.siwpersonale2022.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    void deleteById(Long id);

    List<Evento> findEventosByArtista(Artista artista);


}
