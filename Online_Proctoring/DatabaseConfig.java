package com.onlinepractoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    // This class configures the database connection.

    // This method returns a DataSource object that can be used to connect to the database.
    @Bean
    public DataSource dataSource() {
        // This line creates a new SQLiteDataSource object.
        SQLiteDataSource dataSource = new SQLiteDataSource();

        // This line sets the URL of the database.
        dataSource.setUrl("jdbc:sqlite:database.db");

        // This line returns the dataSource object.
        return dataSource;
    }

    // This method returns a JdbcTemplate object that can be used to execute SQL queries against the database.
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        // This line returns a new JdbcTemplate object that uses the dataSource object.
        return new JdbcTemplate(dataSource);
    }

}
