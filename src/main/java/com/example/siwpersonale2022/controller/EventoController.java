package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.Evento;
import com.example.siwpersonale2022.model.Stadio;
import com.example.siwpersonale2022.model.dto.EditEventoDto;
import com.example.siwpersonale2022.service.ArtistaService;
import com.example.siwpersonale2022.service.EventoService;
import com.example.siwpersonale2022.service.StadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private StadioService stadioService;

    @GetMapping("/admin/eventoForm")
    public String getEventoForm(Model model){

        model.addAttribute("evento", new Evento());
        model.addAttribute("stadioList", stadioService.getAllStadi());
        model.addAttribute("artistaList", artistaService.getAllArtisti());

        return "eventoForm";
    }

    @PostMapping("/admin/eventoForm")
    public String postEventoForm(@Valid @ModelAttribute Evento evento, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 @RequestParam("stadio") String stadioId, @RequestParam("artista") String artistaId){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "errore negli input forniti, ricompila il form");
            return "redirect:/admin/eventoForm";
        }

        evento.setArtista(artistaService.getArtistaById(Long.parseLong(artistaId)));
        Stadio stadio = stadioService.getStadioById(Long.parseLong(stadioId));
        evento.setStadio(stadio);
        evento.setCapienza(stadio.getCapienza());
        eventoService.saveEvento(evento);

        return "redirect:/admin/eventi";
    }

    @GetMapping("/admin/eventi")
    public String getAllEvents(Model model){

        model.addAttribute("eventiList", eventoService.getAllEventi());

        return "allEventsAdmin";
    }

    @GetMapping("/admin/editEvento/{id}")
    public String editEvento(@PathVariable String id, Model model){

        Evento evento = eventoService.getEventoById(Long.parseLong(id));
        EditEventoDto editEventoDto = new EditEventoDto();
        editEventoDto.setData(evento.getData());
        editEventoDto.setPrezzoInt(evento.getPrezzoInt());
        editEventoDto.setId(evento.getId());
        model.addAttribute("evento", editEventoDto);

        return "editEventoForm";
    }

    @PostMapping("/admin/editEvento/{id}")
    public String editEventoPost(@ModelAttribute EditEventoDto editEventoDto, @PathVariable String id, Model model){

        Evento evento = eventoService.getEventoById(Long.parseLong(id));
        evento.setData(editEventoDto.getData());
        evento.setPrezzoInt(editEventoDto.getPrezzoInt());
        eventoService.saveEvento(evento);

        return "redirect:/admin/eventi";
    }


    @GetMapping("/admin/cancellaEvento/{id}")
    public String cancellaEvento(@PathVariable String id, Model model){

        eventoService.deleteEvento(Long.parseLong(id));

        return "redirect:/admin/eventi";

    }
}
