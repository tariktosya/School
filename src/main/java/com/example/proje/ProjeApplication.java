package com.example.proje;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjeApplication.class, args);
    }

}
