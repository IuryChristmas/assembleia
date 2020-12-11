package com.cooperado.assembleia.business;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.model.dto.ErroDTO;
import com.cooperado.assembleia.model.dto.PautaDTO;
import com.cooperado.assembleia.model.enums.SimNaoEnum;
import com.cooperado.assembleia.model.enums.Status;
import com.cooperado.assembleia.repository.PautaRepository;

@Service
public class PautaBusiness {
	
	@Autowired
	private PautaRepository repository;
	
	@Autowired
	private VotoBusiness votoBusiness;
	
	@Autowired
	private MessageSource messageSource;
	
	@Transactional(rollbackFor = Exception.class)
	public Pauta salvar(Pauta pauta) {
		pauta.setDataCriacao(new Date());
		
		return repository.save(pauta);
	}
	
	public ResponseEntity<?> iniciarVotacaoParaPauta(Long id) {
		Optional<Pauta> pautaOptional = repository.findById(id);
		
		String mensagemValidacao = verificarSeExisteInconscistencia(pautaOptional);
		if(!mensagemValidacao.isEmpty()) {
			return ResponseEntity.badRequest().body(ErroDTO.buildMensagemErroSomenteUsuario(mensagemValidacao));
		}
		
		Pauta pauta = pautaOptional.get();
		pauta.setLiberarVotacao(SimNaoEnum.SIM);
		pauta.setDataHoraInicio(new Date());
		
		return ResponseEntity.ok(repository.save(pauta));
	}

	private String verificarSeExisteInconscistencia(Optional<Pauta> pautaOptional) {
		if(!pautaOptional.isPresent()) {
			return messageSource.getMessage("pauta.nao.encontrada", null, LocaleContextHolder.getLocale());
		}
		
		Pauta pauta = pautaOptional.get();
		if(Objects.nonNull(pauta.getLiberarVotacao()) && Objects.nonNull(pauta.getStatus())) {
			return messageSource.getMessage(pauta.getLiberarVotacao().equals(SimNaoEnum.SIM) ? "pauta.ja.iniciada" : "pauta.encerrada", null, 
					LocaleContextHolder.getLocale());
		}
		
		return "";
	}
	
	public Optional<Pauta> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Pauta> buscarTodos(PautaDTO filtro) {
		List<Pauta> pautas = repository.buscarPorFiltro(filtro);
		for(Pauta pauta : pautas) {
			pauta = atualizarVotacoesEncerradas(pauta);
		}
		
		return pautas;
	}

	private Pauta atualizarVotacoesEncerradas(Pauta pauta) {
		if(Objects.nonNull(pauta.getLiberarVotacao()) && pauta.getLiberarVotacao().equals(SimNaoEnum.SIM)) {
			Instant inicioVotacao = pauta.getDataHoraInicio().toInstant();
			Instant tempoAtual = Instant.now();
			Duration tempoDecorrido = Duration.between(inicioVotacao, tempoAtual);
			if(tempoDecorrido.getSeconds() > 60) {
				pauta = this.encerrarPauta(pauta);
			}
		}
		return pauta;
	}
	
	public Pauta encerrarPauta(Pauta pauta) {
		pauta.setLiberarVotacao(SimNaoEnum.NAO);
		Long votosAFavor = votoBusiness.quantidadeVotosAFavor(pauta.getId());
		Long votosContra = votoBusiness.quantidadeVotosContra(pauta.getId());
		
		if(votosAFavor > votosContra) {
			pauta.setStatus(Status.APROVADA);
		} else { 
			pauta.setStatus(Status.REPROVADA);
		}

		return repository.save(pauta);
	}

}
