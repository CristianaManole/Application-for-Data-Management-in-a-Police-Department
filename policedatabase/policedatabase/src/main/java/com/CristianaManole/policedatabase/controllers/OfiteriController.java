/**
 * Controler pentru gestionarea operațiunilor legate de ofițeri.
 * Permite adăugarea, editarea, ștergerea și listarea ofițerilor.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.controllers;

import com.CristianaManole.policedatabase.models.Ofiter;
import com.CristianaManole.policedatabase.services.OfiteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/officers")
public class OfiteriController {

    @Autowired
    private OfiteriRepository repo;

    // Vizualizarea listei de ofițeri
    @GetMapping
    public String showOfficersList(Model model) {
        List<Ofiter> officers = repo.findAllOfficers();
        model.addAttribute("officers", officers);
        return "officers/index";
    }

    // Formularul de adăugare ofițer
    @GetMapping("/add")
    public String addOfficerForm(Model model) {
        model.addAttribute("ofiter", new Ofiter());
        return "officers/AddOfficer";
    }

    // Procesarea adăugării unui ofițer
    @PostMapping("/add")
    public String addOfficer(@ModelAttribute Ofiter ofiter) {
        repo.addOfficer(ofiter);
        return "redirect:/officers";
    }

    // Formularul de editare ofițer
    @GetMapping("/edit/{id}")
    public String editOfficerForm(@PathVariable("id") int id, Model model) {
        Ofiter officer = repo.findOfficerById(id);
        model.addAttribute("ofiter", officer);
        return "officers/EditOfficer";
    }

    // Procesarea editării unui ofițer
    @PostMapping("/edit/{id}")
    public String editOfficer(@PathVariable("id") int id, @ModelAttribute Ofiter ofiter) {
        ofiter.setIdOfiter(id);
        repo.updateOfficer(ofiter);
        return "redirect:/officers";
    }

    // Ștergerea unui ofițer
    @GetMapping("/delete/{id}")
    public String deleteOfficer(@PathVariable("id") int id) {
        repo.deleteOfficerById(id);
        return "redirect:/officers";
    }

    @GetMapping("/cases-per-officer")
    public String showNumberOfCasesPerOfficer(Model model) {
        List<Object[]> officersWithCases = repo.findNumberOfCasesPerOfficer();
        model.addAttribute("officersWithCases", officersWithCases);
        return "officers/CasesPerOfficer";
    }

}