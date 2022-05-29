package com.example.siwpersonale2022.repository;

import com.example.siwpersonale2022.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    void deleteById(Long id);

}
