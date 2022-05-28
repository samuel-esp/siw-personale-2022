package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Utente;
import com.example.siwpersonale2022.model.dto.UtenteRegistrazioneDto;
import com.example.siwpersonale2022.service.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
public class AuthenticationController {

    @Autowired
    private UtenteService utenteService;

    private ObjectError error;

    @GetMapping("/index")
    public String showIndex (Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm (Model model) {
        model.addAttribute("utente", new Utente());
        return "registerUser";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("utente") UtenteRegistrazioneDto utente,
                               BindingResult bindingResult, RedirectAttributes redirAttrs) {

        if(utenteService.getUserByEmail(utente.getEmail())!=null){
            ObjectError error = new ObjectError("email","An account already exists for this email.");
            bindingResult.addError(error);
            log.info(bindingResult.toString());
            redirAttrs.addFlashAttribute("duplicate", "An account already exists for this email.");
            return "redirect:/register";
        }

        if(!bindingResult.hasErrors()) {
            Utente utenteFinale = new Utente();
            utenteFinale.setNome(utente.getNome());
            utenteFinale.setCognome(utente.getCognome());
            utenteFinale.setEmail(utente.getEmail());
            utenteFinale.setPassword(utente.getPassword());
            utenteFinale.setCellulare(utente.getCellulare());
            utenteFinale.setIndirizzo(utente.getIndirizzo());
            if(utenteService.allUsers().size()==0){
                log.info(String.valueOf(utenteService.allUsers().size()));
                utenteFinale.setRole(Utente.ADMIN_ROLE);
            }else{
                utenteFinale.setRole(Utente.DEFAULT_ROLE);
            }
            utenteService.saveUser(utenteFinale);
            return "redirect:/index";
        }
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String showLoginForm (Model model) {
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/afterLogin")
    public String defaultAfterLogin() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        if (utente.getRole().equals(Utente.ADMIN_ROLE)) {
            return "redirect:/admin/home";
        }
        return "redirect:/home";
    }

}
