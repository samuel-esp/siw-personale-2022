package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Citta;
import com.example.siwpersonale2022.repository.CittaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CittaService {

    @Autowired
    private CittaRepository cittaRepository;

    public Citta getCittaById(Long id){
        return cittaRepository.findById(id).get();
    }

    public List<Citta> getAllCitta(){
        return cittaRepository.findAll();
    }

    public void saveCitta(Citta citta){
        cittaRepository.save(citta);
    }

}
