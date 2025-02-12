/**
 * Repozitoriu pentru gestionarea operațiunilor CRUD legate de dosare.
 * Interacționează cu baza de date pentru manipularea tabelei dosare.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.services;

import com.CristianaManole.policedatabase.models.Dosar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DosarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Object[]> findAll() {
        String sql = """
            SELECT d.ID_Dosare, d.Numar_Dosar, d.Descriere, d.Data_inregistrarii,
                   o.Nume AS numeOfiter, o.Prenume AS prenumeOfiter,
                   p.Nume AS numePersoana, p.Prenume AS prenumePersoana
            FROM dosare d
            JOIN ofiteri o ON d.ID_Ofiteri = o.ID_Ofiteri
            JOIN persoane p ON d.ID_Persoana = p.ID_Persoana;
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[] {
                rs.getInt("ID_Dosare"),
                rs.getInt("Numar_Dosar"),
                rs.getString("Descriere"),
                rs.getDate("Data_inregistrarii"),
                rs.getString("numeOfiter"),
                rs.getString("prenumeOfiter"),
                rs.getString("numePersoana"),
                rs.getString("prenumePersoana")
        });
    }


    public void addCase(int numarDosar, String descriere, String dataInregistrarii, Integer idOfiteri, Integer idPersoana) {
        String sql = "INSERT INTO dosare (Numar_Dosar, Descriere, Data_inregistrarii, ID_Ofiteri, ID_Persoana) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, numarDosar, descriere, dataInregistrarii, idOfiteri, idPersoana);
    }

    public void updateCase(int idDosar, int numarDosar, String descriere, String dataInregistrarii, Integer idOfiteri, Integer idPersoana) {
        String sql = "UPDATE dosare SET Numar_Dosar = ?, Descriere = ?, Data_inregistrarii = ?, ID_Ofiteri = ?, ID_Persoana = ? WHERE ID_Dosare = ?";
        jdbcTemplate.update(sql, numarDosar, descriere, dataInregistrarii, idOfiteri, idPersoana, idDosar);
    }

    public void deleteCaseById(int idDosar) {
        String sql = "DELETE FROM dosare WHERE ID_Dosare = ?";
        jdbcTemplate.update(sql, idDosar);
    }

    public Dosar findDosarById(int id) {
        String sql = "SELECT * FROM dosare WHERE ID_Dosare = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Dosar dosar = new Dosar();
            dosar.setIdDosar(rs.getInt("ID_Dosare"));
            dosar.setNumarDosar(rs.getInt("Numar_Dosar"));
            dosar.setDescriere(rs.getString("Descriere"));
            dosar.setDataInregistrarii(rs.getDate("Data_inregistrarii"));
            dosar.setIdOfiteri(rs.getInt("ID_Ofiteri"));
            dosar.setIdPersoana(rs.getInt("ID_Persoana"));
            return dosar;
        }, id);
    }

    public List<Object[]> findCasesByOfficerName(String officerName) {
        String sql = """
        SELECT d.ID_Dosare, d.Numar_Dosar, d.Descriere, d.Data_inregistrarii,
            (SELECT o.Nume FROM ofiteri o WHERE o.ID_Ofiteri = d.ID_Ofiteri) AS numeOfiter,
            (SELECT o.Prenume FROM ofiteri o WHERE o.ID_Ofiteri = d.ID_Ofiteri) AS prenumeOfiter,
            (SELECT p.Nume FROM persoane p WHERE p.ID_Persoana = d.ID_Persoana) AS numePersoana,
            (SELECT p.Prenume FROM persoane p WHERE p.ID_Persoana = d.ID_Persoana) AS prenumePersoana
         FROM dosare d
         WHERE (SELECT o.Nume FROM ofiteri o WHERE o.ID_Ofiteri = d.ID_Ofiteri) = ?;
        
        """;
                return jdbcTemplate.query(sql, new Object[]{officerName}, (rs, rowNum) -> new Object[] {
                        rs.getInt("ID_Dosare"),
                        rs.getInt("Numar_Dosar"),
                        rs.getString("Descriere"),
                        rs.getDate("Data_inregistrarii"),
                        rs.getString("numeOfiter"),
                        rs.getString("prenumeOfiter"),
                        rs.getString("numePersoana"),
                        rs.getString("prenumePersoana")
                });
            }



        }
