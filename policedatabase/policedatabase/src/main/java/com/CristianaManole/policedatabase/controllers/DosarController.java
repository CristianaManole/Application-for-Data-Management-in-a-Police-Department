/**
 * Controler pentru gestionarea operațiunilor legate de dosare.
 * Permite adăugarea, editarea, ștergerea și listarea dosarelor.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.controllers;

import com.CristianaManole.policedatabase.models.Dosar;
import com.CristianaManole.policedatabase.models.Ofiter;
import com.CristianaManole.policedatabase.services.DosarRepository;
import com.CristianaManole.policedatabase.services.OfiteriRepository;
import com.CristianaManole.policedatabase.services.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/cases")
public class DosarController {

    @Autowired
    private DosarRepository dosarRepo;

    @Autowired
    private OfiteriRepository ofiteriRepo;

    @Autowired
    private PeopleRepository peopleRepo;


    // Listarea dosarelor
    @GetMapping
    public String showCaseList(Model model) {
        List<Object[]> cases = dosarRepo.findAll();
        List<Ofiter> officers = ofiteriRepo.findAllOfficers(); // Lista de ofițeri
        model.addAttribute("cases", cases);
        model.addAttribute("officers", officers); // Trimite lista către view
        model.addAttribute("selectedOfficer", ""); // Fără selecție inițială
        return "cases/index";
    }


    // Formularul de adăugare dosar
    @GetMapping("/add")
    public String addCaseForm(Model model) {
        model.addAttribute("dosar", new Dosar());
        model.addAttribute("officers", ofiteriRepo.findAllOfficers());
        model.addAttribute("persons", peopleRepo.findAllNative());
        return "cases/AddCase";
    }

    // Procesarea adăugării unui dosar
    @PostMapping("/add")
    public String addCase(
            @Valid @ModelAttribute Dosar dosar,
            BindingResult result,
            Model model
    ) {
        System.out.println("Add Case method called."); // Debugging

        if (result.hasErrors()) {
            System.out.println("Validation errors found.");
            model.addAttribute("officers", ofiteriRepo.findAllOfficers());
            model.addAttribute("persons", peopleRepo.findAllNative());
            return "cases/AddCase"; // Reafișează formularul în caz de erori
        }

        dosarRepo.addCase(
                dosar.getNumarDosar(),
                dosar.getDescriere(),
                dosar.getDataInregistrarii() != null
                        ? new SimpleDateFormat("yyyy-MM-dd").format(dosar.getDataInregistrarii())
                        : null,
                dosar.getIdOfiteri(),
                dosar.getIdPersoana()
        );

        System.out.println("Case added successfully.");
        return "redirect:/cases"; // Redirecționează la lista dosarelor
    }

    // Formularul de editare dosar
    @GetMapping("/edit/{id}")
    public String editCaseForm(@PathVariable("id") int id, Model model) {
        System.out.println("Edit Case Form method called with ID: " + id); // Debugging
        Dosar caseDetails = dosarRepo.findDosarById(id);

        if (caseDetails == null) {
            model.addAttribute("errorMessage", "Case not found with id " + id);
            return "redirect:/cases";
        }

        model.addAttribute("dosar", caseDetails);
        model.addAttribute("officers", ofiteriRepo.findAllOfficers());
        model.addAttribute("persons", peopleRepo.findAllNative());
        return "cases/EditCase";
    }

    // Procesarea editării unui dosar
    @PostMapping("/edit/{id}")
    public String editCase(
            @PathVariable("id") int id,
            @Valid @ModelAttribute Dosar dosar,
            BindingResult result,
            Model model
    ) {
        System.out.println("Edit Case method called for ID: " + id); // Debugging

        if (result.hasErrors()) {
            System.out.println("Validation errors found.");
            model.addAttribute("officers", ofiteriRepo.findAllOfficers());
            model.addAttribute("persons", peopleRepo.findAllNative());
            return "cases/EditCase";
        }

        String formattedDate = null;
        if (dosar.getDataInregistrarii() != null) {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dosar.getDataInregistrarii());
        }

        dosarRepo.updateCase(
                id,
                dosar.getNumarDosar(),
                dosar.getDescriere(),
                formattedDate,
                dosar.getIdOfiteri(),
                dosar.getIdPersoana()
        );

        System.out.println("Case edited successfully.");
        return "redirect:/cases";
    }

    // Ștergerea unui dosar
    @GetMapping("/delete/{id}")
    public String deleteCase(@PathVariable("id") int id) {
        System.out.println("Delete Case method called for ID: " + id); // Debugging
        dosarRepo.deleteCaseById(id);
        System.out.println("Case deleted successfully.");
        return "redirect:/cases";
    }

    @GetMapping("/search")
    public String searchCasesByOfficerName(
            @RequestParam("officerName") String officerName,
            Model model
    ) {
        List<Object[]> cases;
        if ("All".equalsIgnoreCase(officerName)) {
            // Dacă se selectează "All", returnează toate dosarele
            cases = dosarRepo.findAll();
        } else {
            // Dacă s-a selectat un nume specific, caută doar dosarele acelui ofițer
            cases = dosarRepo.findCasesByOfficerName(officerName);
        }

        List<Ofiter> officers = ofiteriRepo.findAllOfficers();
        model.addAttribute("cases", cases);
        model.addAttribute("officers", officers);
        model.addAttribute("selectedOfficer", officerName); // Officer selectat
        return "cases/index";
    }



}
