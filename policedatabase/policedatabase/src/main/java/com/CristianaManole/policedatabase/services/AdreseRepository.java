/**
 * Repozitoriu pentru gestionarea operațiunilor CRUD legate de adrese.
 * Manipulează datele legate de adrese în baza de date.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.services;

import com.CristianaManole.policedatabase.models.Adresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdreseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obține toate adresele
    public List<Adresa> findAllAddresses() {
        String sql = "SELECT * FROM adrese";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Adresa adresa = new Adresa();
            adresa.setIdAdresa(rs.getInt("ID_Adresa"));
            adresa.setStrada(rs.getString("Strada"));
            adresa.setNumar(rs.getInt("Numar"));
            adresa.setOras(rs.getString("Oras"));
            adresa.setJudet(rs.getString("Judet"));
            return adresa;
        });
    }

    // Adaugă o adresă
    public void addAddress(Adresa adresa) {
        String sql = "INSERT INTO adrese (Strada, Numar, Oras, Judet) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, adresa.getStrada(), adresa.getNumar(), adresa.getOras(), adresa.getJudet());
    }

    // Găsește o adresă după ID
    public Adresa findAddressByID(int idAdresa) {
        String sql = "SELECT * FROM adrese WHERE ID_Adresa = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Adresa adresa = new Adresa();
            adresa.setIdAdresa(rs.getInt("ID_Adresa"));
            adresa.setStrada(rs.getString("Strada"));
            adresa.setNumar(rs.getInt("Numar"));
            adresa.setOras(rs.getString("Oras"));
            adresa.setJudet(rs.getString("Judet"));
            return adresa;
        }, idAdresa);
    }

    // Actualizează o adresă
    public void updateAddress(Adresa adresa) {
        String sql = "UPDATE adrese SET Strada = ?, Numar = ?, Oras = ?, Judet = ? WHERE ID_Adresa = ?";
        jdbcTemplate.update(sql, adresa.getStrada(), adresa.getNumar(), adresa.getOras(), adresa.getJudet(), adresa.getIdAdresa());
    }

    // Șterge o adresă
    public void deleteAddressById(int idAdresa) {
        String sql = "DELETE FROM adrese WHERE ID_Adresa = ?";
        jdbcTemplate.update(sql, idAdresa);
    }
}
