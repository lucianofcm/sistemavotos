package com.sistemavotos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemavotos.domain.DuracaoVotacao;
import com.sistemavotos.domain.Pauta;
import com.sistemavotos.domain.Votacao;
import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.dto.PautaDTO;
import com.sistemavotos.dto.ResultadoVotacaoDTO;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.enumeration.EnumOpcaoVotacao;
import com.sistemavotos.exception.BasicException;
import com.sistemavotos.repository.VotacaoRepository;
import com.sistemavotos.rules.VotacaoRule;

@Service
public class VotacaoServiceImpl implements VotacaoService {

	@Autowired
	private PautaService pautaService;
	@Autowired
	private VotacaoRule votacaoRule;
	@Autowired
	private VotacaoRepository votacaoRepo;
	@Autowired
	private DuracaoVotacaoService duracaoVotacaoService;
	@Autowired
	private AgendadorService agendadorService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String votar(VotacaoDTO votacao) throws BasicException {
		Pauta pautaRecuperada = pautaService.localizarPautaPorID(votacao.getPauta().getId());
		validarVotacao(votacao, pautaRecuperada);
		votacaoRepo.save(Votacao.builder().cpfUsuario(votacao.getCpfUsuario()).opcaoVoto(votacao.getOpcaoVoto())
				.pauta(pautaRecuperada).build());
		return "Votação realizada com sucesso!";
	}

	@Override
	@Transactional
	public DuracaoVotacaoDTO iniciarVotacao(DuracaoVotacaoDTO duracaoVotacao) {
		Pauta pauta = pautaExiste(duracaoVotacao);
		votacaoRule.votacaoJaIniciada(pauta.getId());
		LocalDateTime tempoInicial = LocalDateTime.now();
		agendadorService.execute(duracaoVotacao);
		return new DuracaoVotacaoDTO(duracaoVotacaoService.gravarInicioVotacao(DuracaoVotacao.builder().pauta(pauta).inicioVotacao(tempoInicial)
				.tempoDuracao(duracaoVotacao.getTempoDuracao())
				.fimVotacao(calcularTempoFinalizacao(duracaoVotacao.getTempoDuracao(), tempoInicial)).build()));
	}

	private Pauta pautaExiste(DuracaoVotacaoDTO duracaoVotacao) {
		Pauta pauta = pautaService.localizarPautaPorID(duracaoVotacao.getPauta().getId());
		if (pauta == null) {
			throw new BasicException("A pauta não existe.");
		}
		return pauta;
	}

	private void validarVotacao(VotacaoDTO votacao, Pauta pautaRecuperada) {
		votacaoRule.votacaoNaoIniciada(pautaRecuperada).votacaoEncerrada(pautaRecuperada)
				.usuarioNaoPodeVota(votacao.getCpfUsuario()).usuarioJaVotou(votacao);
	}

	@Override
	public ResultadoVotacaoDTO resultadoVotacao(Integer idPauta) {
		Pauta pautaBanco = pautaService.localizarPautaPorID(idPauta);
		List<Votacao> resultado = votacaoRepo.listarVotacaoPorPauta(pautaBanco.getId());
		DuracaoVotacaoDTO duracaoVotacaoDTO = modelMapper.map(duracaoVotacaoService.localizarPorPauta(idPauta), DuracaoVotacaoDTO.class);
		return ResultadoVotacaoDTO.builder().pauta(new PautaDTO(pautaBanco))
				.qtdeVotosSim(resultado.stream().filter(v -> v.getOpcaoVoto().equals(EnumOpcaoVotacao.S)).count())
				.qtdeVotosNao(resultado.stream().filter(v -> v.getOpcaoVoto().equals(EnumOpcaoVotacao.N)).count()).duracaoDTO(duracaoVotacaoDTO).build();
	}

	private LocalDateTime calcularTempoFinalizacao(Long tempoDuracao, LocalDateTime tempoInicial) {
		return tempoInicial.plusMinutes(tempoDuracao);
	}

}
