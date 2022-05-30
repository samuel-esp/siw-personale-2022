package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Artista;
import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.repository.ArtistaRepository;
import com.example.siwpersonale2022.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public Evento getEventoById(Long id){
        return eventoRepository.findById(id).get();
    }

    public List<Evento> getAllEventiAdmin(){
        return eventoRepository.findAll();
    }

    public List<Evento> getAllEventiUser(){
        List<Evento> eventiList = eventoRepository.findAll();
        List<Evento> resultList = new LinkedList<>();

        for(Evento evento: eventiList){
            if(evento.getCapienza()>0){
                resultList.add(evento);
            }
        }

        return resultList;
    }

    public void saveEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

    public List<Evento> getEventiByArtista(Long id){
        Artista artista = artistaRepository.findById(id).get();
        List<Evento> resultList = eventoRepository.findEventosByArtista(artista);
        List<Evento> eventiList = new ArrayList<>();

        for(Evento evento: resultList){
            if(evento.getCapienza()>0){
                eventiList.add(evento);
            }
        }

        eventiList.sort(new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                return o1.getData().compareTo(o2.getData());
            }
        });

        return eventiList;
    }
}
