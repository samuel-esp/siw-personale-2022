package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Artista;
import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.repository.ArtistaRepository;
import com.example.siwpersonale2022.repository.EventoRepository;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
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

    public List<Evento> getAllEventi(){
        return eventoRepository.findAll();
    }

    public void saveEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

    public List<Evento> getEventiByArtista(Long id){
        Artista artista = artistaRepository.findById(id).get();
        List<Evento> eventiList = eventoRepository.findEventosByArtista(artista);

        eventiList.sort(new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                return o1.getData().compareTo(o2.getData());
            }
        });

        return eventiList;
    }
}
