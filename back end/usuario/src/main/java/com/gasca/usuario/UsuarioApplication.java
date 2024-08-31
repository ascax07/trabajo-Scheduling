package com.gasca.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UsuarioApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsuarioApplication.class, args);
    }
}
