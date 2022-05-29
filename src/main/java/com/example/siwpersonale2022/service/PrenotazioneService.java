package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Prenotazione;
import com.example.siwpersonale2022.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione getPrenotazioneById(Long id){
        return prenotazioneRepository.findById(id).get();
    }

    public List<Prenotazione> getAllPrenotazioni(){
        return prenotazioneRepository.findAll();
    }


}
