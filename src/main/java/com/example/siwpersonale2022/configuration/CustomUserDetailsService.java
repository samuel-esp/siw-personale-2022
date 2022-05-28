package com.example.siwpersonale2022.configuration;

import com.example.siwpersonale2022.model.Utente;
import com.example.siwpersonale2022.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmail(email);
        if(utente==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(utente);
    }


}
