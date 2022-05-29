package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento getEventoById(Long id){
        return eventoRepository.findById(id).get();
    }

    public List<Evento> getAllEventi(){
        return eventoRepository.findAll();
    }

    public void saveEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }
}
