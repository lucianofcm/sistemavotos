package com.sistemavotos.controller;


import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Voto controller.")
@RestController	
@RequestMapping(value = "/v1")
public class VotoController {
	@GetMapping(value = "/teste")
	public String getTaxaEmissaoBilheteAereoChanges(@Valid String listaAuditoriaDTO) {
		return "teste";

	}
}
