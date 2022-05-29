package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Artista;
import com.example.siwpersonale2022.model.Stadio;
import com.example.siwpersonale2022.service.ArtistaService;
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
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/admin/artistaForm")
    public String getArtistaForm(Model model){

        model.addAttribute("stadio", new Stadio());

        return "stadioForm";
    }

    @PostMapping("/admin/artistaForm")
    public String postArtistaForm(@Valid @ModelAttribute Artista artista, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "redirect:/admin/stadioForm";
        }

        artistaService.saveArtista(artista);

        return "redirect:/admin/allEvents";
    }

}
