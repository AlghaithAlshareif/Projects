package com.onlinepractoring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class IncidenceDaoService {

    // The JdbcTemplate object is used to execute SQL queries against the database.
    private JdbcTemplate jdbcTemplate;

    // This constructor injects the JdbcTemplate object into the IncidenceDaoService object.
    @Autowired
    public IncidenceDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        // This method creates the incidences table if it does not already exist.
        createIncidenceTableIfNotExists();
    }

    // This method creates the incidences table if it does not already exist.
    private void createIncidenceTableIfNotExists() {
        // This SQL statement creates the incidences table.
        String sql = "CREATE TABLE IF NOT EXISTS incidences (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_name TEXT," +
                "time TEXT," +
                "incident_type TEXT" +
                ")";
        // This statement executes the SQL statement.
        jdbcTemplate.execute(sql);
    }

    // This method gets all the incidences from the database.
    public List<Incidence> getAllIncidences() {
        // This SQL statement gets all the incidences from the database.
        String sql = "SELECT * FROM incidences";
        // This statement executes the SQL statement and returns a list of incidences.
        return jdbcTemplate.query(sql, new IncidenceRowMapper());
    }

    // This method adds an incidence to the database.
    public void addIncidence(Incidence incidence) {
        // This SQL statement adds an incidence to the database.
        String sql = "INSERT INTO incidences (student_name, time, incident_type) VALUES (?, ?, ?)";
        // This statement executes the SQL statement.
        jdbcTemplate.update(sql, incidence.getStudentName(), incidence.getTime(), incidence.getIncidentType());
    }

    // This class maps a row from the database to an Incidence object.
    private static class IncidenceRowMapper implements RowMapper<Incidence> {
        @Override
        // This method maps a row from the database to an Incidence object.
        public Incidence mapRow(ResultSet rs, int rowNum) throws SQLException {
            Incidence incidence = new Incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setStudentName(rs.getString("student_name"));
            incidence.setTime(rs.getString("time"));
            incidence.setIncidentType(rs.getString("incident_type"));
            return incidence;
        }
    }
}