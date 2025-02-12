/**
 * Repozitoriu pentru gestionarea operațiunilor CRUD legate de ofițeri.
 * Interacționează cu baza de date pentru manipularea tabelei ofițeri.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.services;

import com.CristianaManole.policedatabase.models.Ofiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfiteriRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obține toți ofițerii
    public List<Ofiter> findAllOfficers() {
        String sql = "SELECT * FROM ofiteri";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Ofiter ofiter = new Ofiter();
            ofiter.setIdOfiter(rs.getInt("ID_Ofiteri"));
            ofiter.setNume(rs.getString("Nume"));
            ofiter.setPrenume(rs.getString("Prenume"));
            ofiter.setGrad(rs.getString("Grad"));
            ofiter.setCnp(rs.getString("CNP"));
            ofiter.setSex(rs.getString("Sex"));
            return ofiter;
        });
    }

    // Adaugă un ofițer
    public void addOfficer(Ofiter ofiter) {
        String sql = "INSERT INTO ofiteri (Nume, Prenume, Grad, CNP, Sex) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ofiter.getNume(), ofiter.getPrenume(), ofiter.getGrad(), ofiter.getCnp(), ofiter.getSex());
    }

    // Găsește un ofițer după ID
    public Ofiter findOfficerById(int id) {
        String sql = "SELECT * FROM ofiteri WHERE ID_Ofiteri = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Ofiter ofiter = new Ofiter();
            ofiter.setIdOfiter(rs.getInt("ID_Ofiteri"));
            ofiter.setNume(rs.getString("Nume"));
            ofiter.setPrenume(rs.getString("Prenume"));
            ofiter.setGrad(rs.getString("Grad"));
            ofiter.setCnp(rs.getString("CNP"));
            ofiter.setSex(rs.getString("Sex"));
            return ofiter;
        }, id);
    }

    // Actualizează un ofițer
    public void updateOfficer(Ofiter ofiter) {
        String sql = "UPDATE ofiteri SET Nume = ?, Prenume = ?, Grad = ?, CNP = ?, Sex = ? WHERE ID_Ofiteri = ?";
        jdbcTemplate.update(sql, ofiter.getNume(), ofiter.getPrenume(), ofiter.getGrad(), ofiter.getCnp(), ofiter.getSex(), ofiter.getIdOfiter());
    }

    // Șterge un ofițer
    public void deleteOfficerById(int id) {
        String sql = "DELETE FROM ofiteri WHERE ID_Ofiteri = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Object[]> findNumberOfCasesPerOfficer() {
        String sql = """
        SELECT o.ID_Ofiteri, o.Nume, o.Prenume, (
            SELECT COUNT(*)
            FROM dosare d
            WHERE d.ID_Ofiteri = o.ID_Ofiteri
        ) AS NumberOfCases
        FROM ofiteri o;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("ID_Ofiteri"),
                rs.getString("Nume"),
                rs.getString("Prenume"),
                rs.getInt("NumberOfCases")
        });
    }



}
