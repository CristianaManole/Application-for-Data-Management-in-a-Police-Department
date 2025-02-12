/**
 * Controler pentru gestionarea operațiunilor legate de cetățenii.
 * Permite adăugarea, editarea, ștergerea și listarea cetățeniilor.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.controllers;

import com.CristianaManole.policedatabase.models.Citizenship;
import com.CristianaManole.policedatabase.services.CitizenshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/citizens")
public class CitizenshipController {

    @Autowired
    private CitizenshipRepository repo;

    // Listarea cetățeniilor
    @GetMapping
    public String showCitizenships(Model model) {
        List<Citizenship> citizens = repo.findAllCitizenships();
        model.addAttribute("citizenships", citizens);
        return "/citizens/index";
    }

    // Formular pentru adăugarea unei cetățenii
    @GetMapping("/add")
    public String addCitizenshipForm(Model model) {
        model.addAttribute("citizen", new Citizenship());
        return "/citizens/AddCitizen";
    }

    // Procesarea adăugării unei cetățenii
    @PostMapping("/add")
    public String addCitizenship(@ModelAttribute Citizenship citizen) {
        repo.addCitizenship(citizen);
        return "redirect:/citizens";
    }

    // Formular pentru editarea unei cetățenii
    @GetMapping("/edit/{id}")
    public String editCitizenshipForm(@PathVariable("id") int id, Model model) {
        Citizenship citizen = repo.findCitizenshipById(id);
        model.addAttribute("citizen", citizen);
        return "/citizens/EditCitizen";
    }

    // Procesarea editării unei cetățenii
    @PostMapping("/edit/{id}")
    public String editCitizenship(@PathVariable("id") int id, @ModelAttribute Citizenship citizen) {
        citizen.setIdCetatenie(id);
        repo.updateCitizenship(citizen);
        return "redirect:/citizens";
    }

    // Ștergerea unei cetățenii
    @GetMapping("/delete/{id}")
    public String deleteCitizenship(@PathVariable("id") int id) {
        repo.deleteCitizenshipById(id);
        return "redirect:/citizens";
    }
}
