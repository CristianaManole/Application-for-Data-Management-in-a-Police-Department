/**
 * Clasa pentru reprezentarea unui dosar din sistem.
 * Gestionarea detaliilor despre dosare, precum număr, descriere și ofițer asociat.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */

package com.CristianaManole.policedatabase.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Dosar {
    private int idDosar;
    private int numarDosar;
    private String descriere;

    // Câmpuri suplimentare pentru afişare
    private String numeOfiter;
    private String prenumeOfiter;
    private String numePersoana;
    private String prenumePersoana;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInregistrarii;

    private Integer idOfiteri;
    private Integer idPersoana;

    // Getters și Setters
    public int getIdDosar() {
        return idDosar;
    }

    public void setIdDosar(int idDosar) {
        this.idDosar = idDosar;
    }

    public int getNumarDosar() {
        return numarDosar;
    }

    public void setNumarDosar(int numarDosar) {
        this.numarDosar = numarDosar;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Date getDataInregistrarii() {
        return dataInregistrarii;
    }

    public void setDataInregistrarii(Date dataInregistrarii) {
        this.dataInregistrarii = dataInregistrarii;
    }

    public Integer getIdOfiteri() {
        return idOfiteri;
    }

    public void setIdOfiteri(Integer idOfiteri) {
        this.idOfiteri = idOfiteri;
    }

    public Integer getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(Integer idPersoana) {
        this.idPersoana = idPersoana;
    }

    public String getNumeOfiter() {
        return numeOfiter;
    }

    public void setNumeOfiter(String numeOfiter) {
        this.numeOfiter = numeOfiter;
    }

    public String getPrenumeOfiter() {
        return prenumeOfiter;
    }

    public void setPrenumeOfiter(String prenumeOfiter) {
        this.prenumeOfiter = prenumeOfiter;
    }

    public String getNumePersoana() {
        return numePersoana;
    }

    public void setNumePersoana(String numePersoana) {
        this.numePersoana = numePersoana;
    }

    public String getPrenumePersoana() {
        return prenumePersoana;
    }

    public void setPrenumePersoana(String prenumePersoana) {
        this.prenumePersoana = prenumePersoana;
    }
}
