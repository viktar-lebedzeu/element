package com.element.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Element's test spring Boot application
 * @author Viktar Lebedzeu
 */
@SpringBootApplication
public class TestAppApplication {
    /**
     * The main entry point of Spring boot application
     * @param args Command line arguments
     */
    public static void main(String... args) {
        SpringApplication.run(TestAppApplication.class, args);
    }
}
