package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.model.Prenotazione;
import com.example.siwpersonale2022.model.Utente;
import com.example.siwpersonale2022.repository.EventoRepository;
import com.example.siwpersonale2022.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione getPrenotazioneById(Long id){
        return prenotazioneRepository.findById(id).get();
    }

    public List<Prenotazione> getAllPrenotazioni(){
        return prenotazioneRepository.findAll();
    }

    public void save(Prenotazione prenotazione) {
        prenotazioneRepository.save(prenotazione);
    }

    public void savePrenotazioneEvento(Evento evento, Utente utente) {

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        this.save(prenotazione);

        evento.setCapienza(evento.getCapienza()-1);
        eventoRepository.save(evento);

    }

    public void deletePrenotazioneById(long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).get();
        Evento evento = prenotazione.getEvento();

        prenotazioneRepository.deleteById(id);
        evento.setCapienza(evento.getCapienza() + 1);
        eventoRepository.save(evento);
    }
}
