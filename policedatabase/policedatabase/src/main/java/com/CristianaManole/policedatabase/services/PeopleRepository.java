/**
 * Repozitoriu pentru gestionarea operațiunilor CRUD legate de persoane.
 * Include funcții suplimentare pentru căutarea persoanelor după criterii.
 * @author Manole Cristiana
 * @version 12 Ianuarie 2025
 */


package com.CristianaManole.policedatabase.services;

import com.CristianaManole.policedatabase.models.Adresa;
import com.CristianaManole.policedatabase.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PeopleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Metoda pentru a adăuga o persoană cu SQL nativ (INSERT)
    public void addPerson(String nume, String prenume, String cnp, String sex, String dataNasterii, int idAdresa) {
        String sql = "INSERT INTO persoane (Nume, Prenume, CNP, Sex, Data_nasterii, ID_Adresa) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nume, prenume, cnp, sex, dataNasterii, idAdresa);
    }

    public void updatePerson(String nume, String prenume, String cnp, String sex, String dataNasterii, int idAdresa, int idPersoana) {
        String sql = "UPDATE persoane SET Nume = ?, Prenume = ?, CNP = ?, Sex = ?, Data_nasterii = ?, ID_Adresa = ? WHERE ID_Persoana = ?";
        jdbcTemplate.update(sql, nume, prenume, cnp, sex, dataNasterii, idAdresa, idPersoana);
    }

    public People findPersonByID(int idPersoana) {
        String sql = "SELECT * FROM persoane WHERE ID_Persoana = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            People people = new People();
            people.setIdPersoana(rs.getInt("ID_Persoana"));
            people.setNume(rs.getString("Nume"));
            people.setPrenume(rs.getString("Prenume"));
            people.setCnp(rs.getString("CNP"));
            people.setSex(rs.getString("Sex"));
            people.setDataNasterii(rs.getDate("Data_nasterii"));
            people.setIdAdresa(rs.getInt("ID_Adresa"));
            return people;
        }, idPersoana);
    }

   public void deletePersonById(int idPersoana) {
       String sql = "DELETE FROM persoane WHERE ID_Persoana = ?";
        jdbcTemplate.update(sql, idPersoana);
    }

    // Metoda pentru a obține toate persoanele (SELECT)
    public List<People> findAllNative() {
        String sql = """
        SELECT p.ID_Persoana, p.Nume, p.Prenume, p.CNP, p.Sex, p.Data_nasterii,
               a.Strada, a.Numar, a.Oras, a.Judet
        FROM persoane p
        JOIN adrese a ON p.ID_Adresa = a.ID_Adresa
    """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            People people = new People();
            people.setIdPersoana(rs.getInt("ID_Persoana"));
            people.setNume(rs.getString("Nume"));
            people.setPrenume(rs.getString("Prenume"));
            people.setCnp(rs.getString("CNP"));
            people.setSex(rs.getString("Sex"));
            people.setDataNasterii(rs.getDate("Data_nasterii"));
            people.setAdresa(rs.getString("Strada") + ", " +
                    rs.getInt("Numar") + ", " +
                    rs.getString("Oras") + ", " +
                    rs.getString("Judet")); // Combină datele adresei într-un singur string
            return people;
        });
    }

    public List<Adresa> findAllAddresses() {
        String sql = """
        SELECT ID_Adresa, Strada, Numar, Oras, Judet
        FROM adrese
    """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Adresa adresa = new Adresa();
            adresa.setIdAdresa(rs.getInt("ID_Adresa"));
            adresa.setStrada(rs.getString("Strada"));
            adresa.setNumar(rs.getInt("Numar"));
            adresa.setOras(rs.getString("Oras"));
            adresa.setJudet(rs.getString("Judet")); // Atribuie județul
            return adresa;
        });
    }

    // Obține toate orașele distincte
    public List<String> findAllCities() {
        String sql = "SELECT DISTINCT oras FROM adrese";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<People> findPeopleByCity(String city) {
        String sql = """
    SELECT p.ID_Persoana, p.Nume, p.Prenume, p.CNP, p.Sex, p.Data_nasterii,
           a.Strada, a.Numar, a.Oras, a.Judet
    FROM persoane p
    JOIN adrese a ON p.ID_Adresa = a.ID_Adresa
    WHERE a.Oras = ?
    """;

        // Execută query-ul și mapează rezultatele în obiecte People
        return jdbcTemplate.query(sql, new Object[]{city}, (rs, rowNum) -> {
            // Crearea unui obiect People
            People people = new People();
            people.setIdPersoana(rs.getInt("ID_Persoana"));
            people.setNume(rs.getString("Nume"));
            people.setPrenume(rs.getString("Prenume"));
            people.setCnp(rs.getString("CNP"));
            people.setSex(rs.getString("Sex"));
            people.setDataNasterii(rs.getDate("Data_nasterii"));

            // Construirea adresei ca un string complet
            String adresa = rs.getString("Strada") + ", " +
                    rs.getInt("Numar") + ", " +
                    rs.getString("Oras") + ", " +
                    rs.getString("Judet");
            people.setAdresa(adresa);

            return people;
        });
    }


    public List<People> findPersonWithMostCases() {
        String sql = """
        SELECT p.ID_Persoana, p.Nume, p.Prenume, p.CNP, p.Sex, p.Data_nasterii, p.ID_Adresa
        FROM persoane p
        WHERE p.ID_Persoana = (
            SELECT d.ID_Persoana
            FROM dosare d
            GROUP BY d.ID_Persoana
            ORDER BY COUNT(d.ID_Dosare) DESC
            LIMIT 1
        );
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            People person = new People();
            person.setIdPersoana(rs.getInt("ID_Persoana"));
            person.setNume(rs.getString("Nume"));
            person.setPrenume(rs.getString("Prenume"));
            person.setCnp(rs.getString("CNP")); // Dacă CNP este stocat
            person.setSex(rs.getString("Sex"));
            person.setDataNasterii(rs.getDate("Data_nasterii"));
            person.setIdAdresa(rs.getInt("ID_Adresa")); // Dacă adresa este stocată ca ID
            return person;
        });
    }

    public List<Object[]> findPersonWithMostCitizenships() {
        String sql = """
        SELECT p.ID_Persoana, p.Nume, p.Prenume, COUNT(pc.ID_Cetatenie) AS NumberOfCitizenships
        FROM persoane p
        JOIN persoanecetatenii pc ON p.ID_Persoana = pc.ID_Persoana
        GROUP BY p.ID_Persoana, p.Nume, p.Prenume
        ORDER BY COUNT(pc.ID_Cetatenie) DESC
        LIMIT 1;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("ID_Persoana"),
                rs.getString("Nume"),
                rs.getString("Prenume"),
                rs.getInt("NumberOfCitizenships")
        });
    }






}
