package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Utente;
import com.example.siwpersonale2022.model.dto.UtenteEditDto;
import com.example.siwpersonale2022.repository.UtenteRepository;
import com.example.siwpersonale2022.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/profilo")
    public String getUserProfile(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("utente", utente);

        return "userProfile";
    }

    @GetMapping("/editProfilo")
    public String editUserProfile(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("utente", utente);
        model.addAttribute("utenteEdit", new UtenteEditDto());

        return "editProfilo";

    }

    @PostMapping("/editProfilo")
    public String editUserProfilePost(@Valid @ModelAttribute UtenteEditDto utenteEdit, BindingResult bindingResult,
                                      RedirectAttributes redirAttrs){

        if(bindingResult.hasErrors()){
            redirAttrs.addFlashAttribute("error", "submit the form again, make sure your phone number has exactly 10 numbers");
            return "redirect:/editProfilo";
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        utenteService.editUtente(utenteEdit, utente);
        return "redirect:/profilo";
    }

}
