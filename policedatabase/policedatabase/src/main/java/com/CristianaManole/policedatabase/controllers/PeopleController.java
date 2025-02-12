/**
 * Controler pentru gestionarea operațiunilor legate de persoane.
 * Permite gestionarea persoanelor și căutarea acestora după criterii specifice.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.controllers;

import com.CristianaManole.policedatabase.models.Adresa;
import com.CristianaManole.policedatabase.models.People;
import com.CristianaManole.policedatabase.models.PersonDto;
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
public class PeopleController {

    @Autowired
    private PeopleRepository repo;

    // Afișarea paginii principale
    @GetMapping("/")
    public String showHomePage(
            @RequestParam(value = "city", required = false) String city,
            Model model) {

        // Obține lista de orașe disponibile
        List<String> cities = repo.findAllCities();
        model.addAttribute("cities", cities);

        // Dacă există un oraș selectat, obține persoanele din acel oraș
        if (city != null && !city.isEmpty()) {
            List<People> people = repo.findPeopleByCity(city);
            model.addAttribute("people", people);
        } else {
            model.addAttribute("people", null); // Nicio selecție
        }

        model.addAttribute("selectedCity", city); // Orașul selectat
        return "index"; // Pagina principală
    }

    // Vizualizarea listei de persoane
    @GetMapping("/people")
    public String showPeopleList(Model model) {
        List<People> peoples = repo.findAllNative();
        List<People> personWithMostCases = repo.findPersonWithMostCases();
        List<Object[]> personWithMostCitizenships = repo.findPersonWithMostCitizenships();

        model.addAttribute("people", peoples);
        model.addAttribute("personWithMostCases", personWithMostCases.get(0)); // Prima persoană din listă
        model.addAttribute("personWithMostCitizenships", personWithMostCitizenships.get(0));
        return "/people/index";
    }

    // Formularul de adăugare persoană
    @GetMapping("/people/add")
    public String addPersonForm(Model model) {
        model.addAttribute("personDto", new PersonDto());
        List<Adresa> adrese = repo.findAllAddresses(); // Obține lista de adrese
        model.addAttribute("adrese", adrese); // Transmite lista de adrese către view
        return "/people/AddPeople";
    }

    // Procesarea adăugării unei persoane
    @PostMapping("/people/add")
    public String addPerson(
            @Valid @ModelAttribute PersonDto personDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("adrese", repo.findAllAddresses()); // Reîncarcă lista de adrese
            return "/people/AddPeople";
        }

        // Formatează data de naștere
        String formattedDate = null;
        if (personDto.getDataNasterii() != null) {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(personDto.getDataNasterii());
        }

        // Salvăm persoana folosind JdbcTemplate
        repo.addPerson(
                personDto.getNume(),
                personDto.getPrenume(),
                personDto.getCnp(),
                personDto.getSex(),
                formattedDate, // Dacă data este nulă, va rămâne nulă
                personDto.getIdAdresa()
        );

        return "redirect:/people";  // Redirect la lista de persoane
    }

    // Formularul de editare persoană
    @GetMapping("/people/edit/{id}")
    public String editPersonForm(@PathVariable("id") int id, Model model) {
        People person = repo.findPersonByID(id); // Obține persoana din baza de date

        if (person == null) {
            // Dacă persoana nu există, redirecționează la lista de persoane sau afișează o eroare
            model.addAttribute("errorMessage", "No people found with id " + id);
            return "redirect:/people"; // Sau returnează o pagină specială de eroare
        }

        List<Adresa> adrese = repo.findAllAddresses();
        model.addAttribute("personDto", person);
        model.addAttribute("adrese", adrese);
        return "/people/EditPeople"; // Pagina de editare
    }

    // Procesarea editării unei persoane
    @PostMapping("/people/edit/{id}")
    public String editPerson(
            @PathVariable("id") int id,
            @Valid @ModelAttribute PersonDto personDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("adrese", repo.findAllAddresses()); // Reîncarcă lista de adrese
            return "/people/EditPeople"; // Dacă sunt erori, reafișează formularul
        }

        String formattedDate = null;
        if (personDto.getDataNasterii() != null) {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(personDto.getDataNasterii());
        }

        // Actualizează persoana în baza de date
        repo.updatePerson(
                personDto.getNume(),
                personDto.getPrenume(),
                personDto.getCnp(),
                personDto.getSex(),
                formattedDate,
                personDto.getIdAdresa(),
                id
        );

        return "redirect:/people"; // Redirect către lista de persoane
    }

    // Ștergerea unei persoane
    @GetMapping("/people/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        repo.deletePersonById(id); // Șterge persoana pe baza ID-ului
        return "redirect:/people";  // Redirecționează la lista de persoane
    }



}
