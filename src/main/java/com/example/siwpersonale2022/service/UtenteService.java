package com.example.siwpersonale2022.service;

import com.example.siwpersonale2022.model.Utente;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.siwpersonale2022.repository.UtenteRepository;

import java.util.Collection;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtenteRepository utenteRepository;


    public Utente getUserByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public List<Utente> allUsers() {
        return utenteRepository.findAll();
    }

    public void saveUser(Utente utente) {
        String standardPassword = utente.getPassword();
        String encodedPassword = passwordEncoder.encode(standardPassword);
        utente.setPassword(encodedPassword);
        utenteRepository.save(utente);
    }

    public List<Utente> getAllUsers() {
        return utenteRepository.findAll();
    }

    public Utente getUserById(Long id){
        return utenteRepository.findById(id).get();
    }
}
