/**
 * Controler pentru gestionarea relațiilor dintre persoane și cetățenii.
 * Permite asocierea și eliminarea cetățeniilor pentru persoane.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.controllers;

import com.CristianaManole.policedatabase.services.PersoaneCetateniiRepository;
import com.CristianaManole.policedatabase.services.PeopleRepository;
import com.CristianaManole.policedatabase.services.CitizenshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/persoane-cetatenii")
public class PersoaneCetateniiController {

    @Autowired
    private PersoaneCetateniiRepository repo;

    @Autowired
    private PeopleRepository peopleRepo;

    @Autowired
    private CitizenshipRepository citizenshipRepo;

    @GetMapping
    public String showRelations(Model model) {
        model.addAttribute("relations", repo.findAllRelations());
        return "persoane_cetatenii/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("persoane", peopleRepo.findAllNative());
        model.addAttribute("cetatenii", citizenshipRepo.findAllCitizenships());
        return "persoane_cetatenii/AddRelation";
    }

    @PostMapping("/add")
    public String addRelation(@RequestParam int idPersoana, @RequestParam int idCetatenie) {
        repo.addRelation(idPersoana, idCetatenie);
        return "redirect:/persoane-cetatenii";
    }


    @GetMapping("/delete-choose")
    public String showDeleteChoosePage(@RequestParam int idPersoana, Model model) {
        // Obține persoana și cetățeniile asociate
        model.addAttribute("idPersoana", idPersoana);
        model.addAttribute("cetatenii", repo.findNationalitiesByPerson(idPersoana));
        return "persoane_cetatenii/DeleteChoose";
    }

    @PostMapping("/delete-choose")
    public String deleteChosenNationality(@RequestParam int idPersoana, @RequestParam int idCetatenie) {
        repo.deleteRelation(idPersoana, idCetatenie);
        return "redirect:/persoane-cetatenii";
    }

    @GetMapping("/people-with-multiple-citizenships")
    public String showPeopleWithMultipleCitizenships(Model model) {
        List<Object[]> people = repo.findPeopleWithMultipleCitizenships();
        model.addAttribute("people", people);
        return "persoane_cetatenii/MultipleCitizenships";
    }

}
