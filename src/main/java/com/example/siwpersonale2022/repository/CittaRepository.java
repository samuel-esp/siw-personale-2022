package com.example.siwpersonale2022.repository;

import com.example.siwpersonale2022.model.Citta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CittaRepository extends JpaRepository<Citta, Long> {

}
