package com.sistemavotos;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan("com.sistemavotos.*")
@EnableRabbit
public class SistemavotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemavotosApplication.class, args);
		log.info("Sistemas de criação de pauta e registro de votação.");
	}

}
