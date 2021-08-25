package com.sistemavotos.controller;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(value = "/versao", tags = { "versao" })
@SwaggerDefinition(tags = {
		@Tag(name = "Serviço para retornar a versao da API", description = "Serviço para retornar a versao da API") })
@RestController
@RequestMapping("/versao")
public class Versao {

	@ApiOperation(value = "Serviço para retornar a versao da API")
	@GetMapping(path = "/")
	public ResponseEntity<String> exibirVersao() {
		try {
			MavenXpp3Reader pomReader = new MavenXpp3Reader();
			Model model = pomReader
					.read(new InputStreamReader(new FileInputStream("pom.xml"), StandardCharsets.UTF_8));
			return new ResponseEntity<>(model.getVersion(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
