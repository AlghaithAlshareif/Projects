package com.onlinepractoring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// This class provides a service for accessing students data in a database.
@Component
public class StudentDaoService {

    // This field is an instance of the JdbcTemplate class, which is used to execute SQL queries against a database.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // This constructor initializes the JdbcTemplate field and creates the students table if it does not already exist.
    @Autowired
    public StudentDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createStudentsTableIfNotExists();
    }

    // This method creates the students table if it does not already exist.
    private void createStudentsTableIfNotExists() {
        // This SQL statement creates the students table if it does not already exist.
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "admission_number TEXT UNIQUE" +
                ")";

        // This line executes the SQL statement.
        jdbcTemplate.execute(sql);
    }

    // This method returns a list of all students in the database.
    public List<Student> getAllStudents() {
        // This SQL statement selects all students from the database.
        String sql = "SELECT * FROM students";

        // This line executes the SQL statement and returns a list of Student objects.
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    // This method adds a new student to the database.
    public void addStudent(Student student) {
        // This SQL statement inserts a new student into the database.
        String sql = "INSERT INTO students (name, admission_number) VALUES (?, ?)";

        // This line executes the SQL statement and passes in the student's name and admission number.
        jdbcTemplate.update(sql, student.getName(), student.getAdmissionNumber());
    }

    // This class maps a row from the students table to a Student object.
    private static class StudentRowMapper implements RowMapper<Student> {

        // This method maps a row from the students table to a Student object.
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            // This line creates a new Student object.
            Student student = new Student();

            // This line sets the student's id.
            student.setId(rs.getInt("id"));

            // This line sets the student's name.
            student.setName(rs.getString("name"));

            // This line sets the student's admission number.
            student.setAdmissionNumber(rs.getString("admission_number"));

            // This line returns the Student object.
            return student;
        }
    }
}

