package com.sistemavotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sistemavotos.*")
public class SistemavotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemavotosApplication.class, args);
	}

}
