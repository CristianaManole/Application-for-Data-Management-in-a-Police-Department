/**
 * Controler pentru gestionarea operațiunilor legate de adrese.
 * Permite adăugarea, editarea, ștergerea și listarea adreselor.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.controllers;

import com.CristianaManole.policedatabase.models.Adresa;
import com.CristianaManole.policedatabase.services.AdreseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/addresses")
public class AdreseController {

    @Autowired
    private AdreseRepository repo;

    // Vizualizarea listei de adrese
    @GetMapping
    public String showAddressList(Model model) {
        List<Adresa> addresses = repo.findAllAddresses();
        model.addAttribute("addresses", addresses);
        return "/addresses/index";
    }

    // Formularul de adăugare adresă
    @GetMapping("/add")
    public String addAddressForm(Model model) {
        model.addAttribute("adresa", new Adresa());
        return "/addresses/AddAddress";
    }

    // Procesarea adăugării unei adrese
    @PostMapping("/add")
    public String addAddress(
            @Valid @ModelAttribute Adresa adresa,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/addresses/AddAddress"; // Reafișează formularul în caz de erori
        }

        repo.addAddress(adresa);
        return "redirect:/addresses"; // Redirect la lista de adrese
    }

    // Formularul de editare adresă
    @GetMapping("/edit/{id}")
    public String editAddressForm(@PathVariable("id") int id, Model model) {
        Adresa address = repo.findAddressByID(id);
        if (address == null) {
            model.addAttribute("errorMessage", "Address not found with id " + id);
            return "redirect:/addresses";
        }

        model.addAttribute("adresa", address);
        return "/addresses/EditAddress";
    }

    // Procesarea editării unei adrese
    @PostMapping("/edit/{id}")
    public String editAddress(
            @PathVariable("id") int id,
            @Valid @ModelAttribute Adresa adresa,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "/addresses/EditAddress"; // Reafișează formularul în caz de erori
        }


        adresa.setIdAdresa(id);
        repo.updateAddress(adresa);
        return "redirect:/addresses"; // Redirect către lista de adrese
    }

    // Ștergerea unei adrese
    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") int id) {
        repo.deleteAddressById(id);
        return "redirect:/addresses";
    }
}
