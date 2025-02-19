package com.example.oxquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OxQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(OxQuizApplication.class, args);
        System.out.println("start~~~~~~~~~~~~");
    }

}
