package com.sistemavotos.controller;

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

import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.dto.ResultadoVotacaoDTO;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.messageria.VotacaoSenderService;
import com.sistemavotos.service.AgendadorService;
import com.sistemavotos.service.PautaService;
import com.sistemavotos.service.VotacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Votacão controller.")
@RestController
@RequestMapping(value = "/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;
	
	@PostMapping("/iniciarVotacao")
	@ApiOperation(value= "Inicia a votação por tempo determinado")
    public ResponseEntity<DuracaoVotacaoDTO> iniciarVotacao(@RequestBody @Valid DuracaoVotacaoDTO votacao){
		return ResponseEntity.status(HttpStatus.OK).body(votacaoService.iniciarVotacao(votacao));
    }
	

	@PostMapping	
	@ApiOperation(value= "Realiza votacao caso todos as regras tenham sido validadas.")
    public ResponseEntity<String> votar(@RequestBody @Valid VotacaoDTO votacao){
		return ResponseEntity.status(HttpStatus.OK).body(votacaoService.votar(votacao));
    }
	
	
	@GetMapping(value = "/resultadoVotacao/{idPauta}")	
	@ApiOperation(value= "Apresenta o resultado da votação de uma pauta")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "A pauta não foi localizada.") })
    public ResponseEntity<ResultadoVotacaoDTO> resultadoVotacao(@PathVariable Integer idPauta){
		return ResponseEntity.status(HttpStatus.OK).body(votacaoService.resultadoVotacao(idPauta));
    }
	
}

