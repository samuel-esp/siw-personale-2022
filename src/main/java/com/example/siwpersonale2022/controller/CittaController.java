package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Citta;
import com.example.siwpersonale2022.service.CittaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CittaController {

    @Autowired
    private CittaService cittaService;

    @GetMapping("/admin/cittaForm")
    public String getCittaForm(Model model){

        model.addAttribute("citta", new Citta());

        return "cittaForm";
    }

    @PostMapping("/admin/cittaForm")
    public String postCittaForm(@Valid @ModelAttribute Citta citta, BindingResult bindingResult, RedirectAttributes redirectAttributes){

       if(bindingResult.hasErrors()){
           redirectAttributes.addFlashAttribute("error", "errore negli input forniti, ricompila il form");
           return "redirect:/admin/cittaForm";
       }

       cittaService.saveCitta(citta);

       return "redirect:/admin/eventi";
    }
}
