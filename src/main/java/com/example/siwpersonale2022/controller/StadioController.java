package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Citta;
import com.example.siwpersonale2022.model.Stadio;
import com.example.siwpersonale2022.service.CittaService;
import com.example.siwpersonale2022.service.StadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class StadioController {

    @Autowired
    private StadioService stadioService;

    @Autowired
    private CittaService cittaService;

    @GetMapping("/admin/stadioForm")
    public String getStadioForm(Model model){

        model.addAttribute("stadio", new Stadio());
        model.addAttribute("cittaList", cittaService.getAllCitta());

        return "stadioForm";
    }

    @PostMapping("/admin/stadioForm")
    public String postStadioForm(@Valid @ModelAttribute Stadio stadio, @RequestParam("citta") String id,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "errore negli input forniti, ricompila il form");
            return "redirect:/admin/stadioForm";
        }

        stadio.setCitta(cittaService.getCittaById(Long.parseLong(id)));
        stadioService.saveStadio(stadio);

        return "redirect:/admin/eventi";
    }

}
