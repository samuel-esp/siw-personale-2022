package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Stadio;
import com.example.siwpersonale2022.repository.StadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StadioService {

    @Autowired
    private StadioRepository stadioRepository;

    public Stadio getStadioById(Long id){
        return stadioRepository.findById(id).get();
    }

    public List<Stadio> getAllStadi(){
        return stadioRepository.findAll();
    }


    public void saveStadio(Stadio stadio) {
        stadioRepository.save(stadio);
    }
}
