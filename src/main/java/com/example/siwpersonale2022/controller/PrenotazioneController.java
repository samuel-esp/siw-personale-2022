package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.model.Prenotazione;
import com.example.siwpersonale2022.model.Utente;
import com.example.siwpersonale2022.service.EventoService;
import com.example.siwpersonale2022.service.PrenotazioneService;
import com.example.siwpersonale2022.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

@Controller
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EventoService eventoService;

    @PostMapping("/prenotazione/evento/{id}")

    public String compraEventoId(@PathVariable String id, Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());

        Evento evento = eventoService.getEventoById(Long.parseLong(id));

        prenotazioneService.savePrenotazioneEvento(evento, utente);
        return "redirect:/eventi";

    }

    @GetMapping("/prenotazioni")
    public String getAllPrenotazioni(Model model){

        model.addAttribute("prenotazioniLista", prenotazioneService.getAllPrenotazioni());

        return "allPrenotazioni";

    }

    @GetMapping("/prenotazione/cancella/{id}")
    public String cancellaPrenotazione(@PathVariable String id, Model model){


        prenotazioneService.deletePrenotazioneById(Long.parseLong(id));


        return "redirect:/prenotazioni";

    }
}
