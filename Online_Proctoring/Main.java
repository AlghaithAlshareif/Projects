package com.onlinepractoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); //starts the whole Spring framework and the model
    }

}
