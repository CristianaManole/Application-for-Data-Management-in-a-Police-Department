/**
 * Repozitoriu pentru gestionarea relațiilor dintre persoane și cetățenii.
 * Manipulează datele many-to-many între persoane și cetățenii în baza de date.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersoaneCetateniiRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obține toate relațiile (afișare relații cu naționalități)
    public List<Object[]> findAllRelations() {
        String sql = """
                SELECT p.ID_Persoana, p.Nume, p.Prenume,
                       GROUP_CONCAT(c.Nationalitate SEPARATOR ', ') AS cetatenii
                FROM persoanecetatenii pc
                JOIN persoane p ON pc.ID_Persoana = p.ID_Persoana
                JOIN cetatenii c ON pc.ID_Cetatenie = c.ID_Cetatenie
                GROUP BY p.ID_Persoana;
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("ID_Persoana"),
                rs.getString("Nume"),
                rs.getString("Prenume"),
                rs.getString("cetatenii")
        });
    }

    // Adaugă o relație
    public void addRelation(int idPersoana, int idCetatenie) {
        String sql = "INSERT INTO persoanecetatenii (ID_Persoana, ID_Cetatenie) VALUES (?, ?)";
        jdbcTemplate.update(sql, idPersoana, idCetatenie);
    }

    // Șterge o relație
    public void deleteRelation(int idPersoana, int idCetatenie) {
        String sql = "DELETE FROM persoanecetatenii WHERE ID_Persoana = ? AND ID_Cetatenie = ?";
        jdbcTemplate.update(sql, idPersoana, idCetatenie);
    }

    // Obține lista de naționalități
    public List<String> findAllNationalities() {
        String sql = "SELECT Nationalitate FROM cetatenii";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("Nationalitate"));
    }

    public List<Object[]> findNationalitiesByPerson(int idPersoana) {
        String sql = """
            SELECT c.ID_Cetatenie, c.Nationalitate
            FROM persoanecetatenii pc
            JOIN cetatenii c ON pc.ID_Cetatenie = c.ID_Cetatenie
            WHERE pc.ID_Persoana = ?;
            """;

        return jdbcTemplate.query(sql, new Object[]{idPersoana}, (rs, rowNum) -> new Object[]{
                rs.getInt("ID_Cetatenie"),
                rs.getString("Nationalitate")
        });
    }

    public List<Object[]> findPeopleWithMultipleCitizenships() {
        String sql = """
        SELECT p.ID_Persoana, p.Nume, p.Prenume
        FROM persoane p
        WHERE p.ID_Persoana IN (
            SELECT pc.ID_Persoana
            FROM persoanecetatenii pc
            GROUP BY pc.ID_Persoana
            HAVING COUNT(pc.ID_Cetatenie) > 1
        );
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("ID_Persoana"),
                rs.getString("Nume"),
                rs.getString("Prenume")
        });
    }




}
