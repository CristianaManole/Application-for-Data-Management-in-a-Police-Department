/**
 * Clasa pentru reprezentarea unei cetățenii din sistem.
 * Include informații despre țară și naționalitate.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.models;

public class Citizenship {
    private int idCetatenie;
    private String tara;
    private String nationalitate;

    // Getters și setters
    public int getIdCetatenie() {
        return idCetatenie;
    }

    public void setIdCetatenie(int idCetatenie) {
        this.idCetatenie = idCetatenie;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getNationalitate() {
        return nationalitate;
    }

    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }
}
