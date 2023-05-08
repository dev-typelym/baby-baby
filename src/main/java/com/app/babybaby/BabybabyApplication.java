package com.app.babybaby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BabybabyApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.app.babybaby.BabybabyApplication.class, args);
    }

}
