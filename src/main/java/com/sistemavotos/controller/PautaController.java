package com.sistemavotos.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.PautaDTO;
import com.sistemavotos.service.PautaService;

import io.swagger.annotations.Api;

@Api(tags = "Voto controller.")
@RestController
@RequestMapping(value = "/pauta")
public class PautaController {
	@Autowired
	private PautaService pautaService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/teste")
	public PautaDTO getTaxaEmissaoBilheteAereoChanges(@PathVariable Integer idPauta) {
		return null;

	}

	@PostMapping
    public Pauta cadastrarPauta(@RequestBody @Valid PautaDTO pauta){
        return pautaService.cadastrarPauta(modelMapper.map(pauta, Pauta.class));
    }
}

