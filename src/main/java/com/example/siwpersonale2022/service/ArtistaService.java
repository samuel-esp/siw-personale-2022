package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Artista;
import com.example.siwpersonale2022.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista getArtistaById(Long id){
        return artistaRepository.findById(id).get();
    }

    public List<Artista> getAllArtisti(){
        return artistaRepository.findAll();
    }


    public void saveArtista(Artista artista) {
        artistaRepository.save(artista);
    }
}
