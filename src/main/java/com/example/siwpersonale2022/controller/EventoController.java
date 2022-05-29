package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.model.Stadio;
import com.example.siwpersonale2022.service.EventoService;
import com.example.siwpersonale2022.service.StadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/admin/eventoForm")
    public String getEventoForm(Model model){

        model.addAttribute("evento", new Evento());

        return "eventoForm";
    }

    @PostMapping("/admin/eventoForm")
    public String postEventoForm(@Valid @ModelAttribute Evento evento, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "redirect:/admin/eventoForm";
        }

        eventoService.saveEvento(evento);

        return "redirect:/admin/allEvents";
    }


}
