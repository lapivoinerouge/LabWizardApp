package com.lab.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(GeneratorApplication.class, args);
        TokenGenerator generator = new TokenGenerator();
        generator.refreshToken();
    }
}
