/**
 * Clasa pentru gestionarea relației many-to-many dintre persoane și cetățenii.
 * Asociază o persoană cu una sau mai multe cetățenii.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.models;

public class PersoanaCetatenii {
    private int idPersoana;
    private int idCetatenie;

    // Getters și setters
    public int getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(int idPersoana) {
        this.idPersoana = idPersoana;
    }

    public int getIdCetatenie() {
        return idCetatenie;
    }

    public void setIdCetatenie(int idCetatenie) {
        this.idCetatenie = idCetatenie;
    }
}
