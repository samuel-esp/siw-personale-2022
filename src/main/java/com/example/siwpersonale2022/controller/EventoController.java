package com.example.siwpersonale2022.controller;

import com.example.siwpersonale2022.model.*;
import com.example.siwpersonale2022.model.dto.EditEventoDto;
import com.example.siwpersonale2022.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private StadioService stadioService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CittaService cittaService;

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

        model.addAttribute("eventiList", eventoService.getAllEventiAdmin());

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

    @GetMapping("/eventi")
    public String getEventiUser(Model model){

        model.addAttribute("eventiList", eventoService.getAllEventiUser());

        return "allEventsUser";

    }

    @GetMapping("/evento/info/{id}")
    public String getEventoInfo(@PathVariable String id, Model model){

        Evento evento = eventoService.getEventoById(Long.parseLong(id));
        model.addAttribute("evento", evento);

        return "eventoInfo";
    }

    @GetMapping("/eventiCitta")
    public String getEventiByCittaForm(Model model){

        model.addAttribute("cittaList", cittaService.getAllCitta());

        return "eventoCittaForm";
    }

    @GetMapping("/eventiArtista")
    public String getEventiByArtistaForm(Model model){

        model.addAttribute("artistiList", artistaService.getAllArtisti());

        return "eventoArtistaForm";
    }

    @PostMapping("/eventiCitta/find")
    public String getEventiByCitta(@RequestParam("citta") String id, Model model){

        Citta citta = cittaService.getCittaById(Long.parseLong(id));
        List<Evento> eventoList = eventoService.getAllEventiUser();
        List<Evento> resultList = new LinkedList<>();
        for(Evento evento: eventoList){
            if(evento.getStadio().getCitta()==citta){
                if(evento.getCapienza()>0) {
                    resultList.add(evento);
                }
            }
        }

        resultList.sort(new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                return o1.getData().compareTo(o2.getData());
            }
        });

        model.addAttribute("eventiList", resultList);

        return "allEventsUser";
    }


    @PostMapping("/eventiArtista/find/")
    public String getEventiByArtista(@RequestParam("artista") String id, Model model){

        List<Evento> resultList = eventoService.getEventiByArtista(Long.parseLong(id));
        model.addAttribute("eventiList", resultList);

        return "allEventsUser";
    }

}
