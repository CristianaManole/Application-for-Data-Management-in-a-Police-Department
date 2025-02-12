/**
 * Clasa pentru reprezentarea unui ofițer din sistem.
 * Stochează informațiile personale și profesionale ale ofițerilor.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.models;

public class Ofiter {
    private int idOfiter;
    private String nume;
    private String prenume;
    private String grad;
    private String cnp;
    private String sex;

    // Getters și Setters
    public int getIdOfiter() {
        return idOfiter;
    }

    public void setIdOfiter(int idOfiter) {
        this.idOfiter = idOfiter;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
