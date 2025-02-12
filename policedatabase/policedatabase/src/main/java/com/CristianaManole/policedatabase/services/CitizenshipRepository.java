/**
 * Repozitoriu pentru gestionarea operațiunilor CRUD legate de cetățenii.
 * Permite manipularea datelor legate de cetățenii în baza de date.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.services;

import com.CristianaManole.policedatabase.models.Citizenship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitizenshipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obține toate cetățeniile
    public List<Citizenship> findAllCitizenships() {
        String sql = "SELECT * FROM cetatenii";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Citizenship citizenship = new Citizenship();
            citizenship.setIdCetatenie(rs.getInt("ID_Cetatenie"));
            citizenship.setTara(rs.getString("Tara"));
            citizenship.setNationalitate(rs.getString("Nationalitate"));
            return citizenship;
        });
    }

    // Adaugă o cetățenie
    public void addCitizenship(Citizenship citizenship) {
        String sql = "INSERT INTO cetatenii (Tara, Nationalitate) VALUES (?, ?)";
        jdbcTemplate.update(sql, citizenship.getTara(), citizenship.getNationalitate());
    }

    // Găsește cetățenia după ID
    public Citizenship findCitizenshipById(int id) {
        String sql = "SELECT * FROM cetatenii WHERE ID_Cetatenie = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Citizenship citizenship = new Citizenship();
            citizenship.setIdCetatenie(rs.getInt("ID_Cetatenie"));
            citizenship.setTara(rs.getString("Tara"));
            citizenship.setNationalitate(rs.getString("Nationalitate"));
            return citizenship;
        }, id);
    }

    // Actualizează o cetățenie
    public void updateCitizenship(Citizenship citizenship) {
        String sql = "UPDATE cetatenii SET Tara = ?, Nationalitate = ? WHERE ID_Cetatenie = ?";
        jdbcTemplate.update(sql, citizenship.getTara(), citizenship.getNationalitate(), citizenship.getIdCetatenie());
    }

    // Șterge o cetățenie
    public void deleteCitizenshipById(int id) {
        String sql = "DELETE FROM cetatenii WHERE ID_Cetatenie = ?";
        jdbcTemplate.update(sql, id);
    }
}
