/**
 * Clasa pentru reprezentarea unei adrese din sistem.
 * Include detalii despre stradă, număr, oraș și județ.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Adresa {
    private int idAdresa;

    @NotBlank(message = "Street is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Street can only contain alphanumeric characters and spaces")
    private String strada;

    @NotNull(message = "Number is required")
    @Min(value = 1, message = "Number must be a positive integer")
    private Integer numar;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "City can only contain alphanumeric characters and spaces")
    private String oras;

    @NotBlank(message = "County is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "County can only contain alphanumeric characters and spaces")
    private String judet;

    // Getters și Setters
    public int getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(int idAdresa) {
        this.idAdresa = idAdresa;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

}








