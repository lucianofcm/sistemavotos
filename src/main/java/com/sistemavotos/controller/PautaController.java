package com.sistemavotos.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Voto controller.")
@RestController
@RequestMapping(value = "/pauta")
public class PautaController {
	@Autowired
	private PautaService pautaService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{idPauta}")
	@ApiOperation(value= "Recupera uma pauta a partir do seu ID.")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "A pauta não foi localizada.") })	
	public PautaDTO localizarPautaPorId(@PathVariable Integer idPauta) {
		return modelMapper.map(pautaService.localizarPautaPorID(idPauta), PautaDTO.class);

	}
	
	@GetMapping(value = "/listarTodasPautas")
	@ApiOperation(value= "Lista todas as pautas cadastradas")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "A pauta não foi localizada.") })	
	public ResponseEntity<List<PautaDTO>> listarTodasPautas() {
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.listarPautas());
	}

	@PostMapping
	@ApiOperation(value= "A partir do preenchimentos dos campos titulo, descricao será gerada uma nova pauta com o status ATIVA")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Os campos título e descrição devem ser preenchidos. ") })
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody @Valid PautaDTO pauta){
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.cadastrarPauta(modelMapper.map(pauta, Pauta.class)));
    }
	
	@GetMapping(value = "/listarPautasNaoInicidas")
	@ApiOperation(value= "Lista todas as pautas que ainda não foram iniciadas.")
    public ResponseEntity<List<PautaDTO>> listarPautasNaoIniciads(@RequestBody @Valid PautaDTO pauta){
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.listarPautasNaoIniciada());
    }
}

