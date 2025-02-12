/**
 * Clasa pentru transferul datelor unei persoane între interfața utilizator și backend.
 * Include validări pentru câmpuri și este utilizată pentru operațiunile de adăugare și editare.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class PersonDto {


    private Integer idPersoana;

    @NotEmpty(message = "The last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    private String nume;

    @NotEmpty(message = "The first name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    private String prenume;

    @NotEmpty(message = "The CNP is required")
    @Pattern(regexp = "\\d{13}", message = "CNP must be exactly 13 digits")
    private String cnp;

    @NotEmpty(message = "The SEX is required")
    @Pattern(regexp = "[MF]", message = "Sex must be 'M' or 'F'")
    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNasterii;

    @Min(value = 1, message = "The AddressID must be a positive number")
    private int idAdresa;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public int getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(int idAdresa) {
        this.idAdresa = idAdresa;
    }

    public Integer getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(Integer idPersoana) {
        this.idPersoana = idPersoana;
    }
}
