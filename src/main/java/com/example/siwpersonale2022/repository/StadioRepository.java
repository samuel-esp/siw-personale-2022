package com.example.siwpersonale2022.repository;

import com.example.siwpersonale2022.model.Stadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadioRepository extends JpaRepository<Stadio, Long> {
}
