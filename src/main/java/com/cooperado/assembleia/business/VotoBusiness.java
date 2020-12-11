package com.cooperado.assembleia.business;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.model.Voto;
import com.cooperado.assembleia.model.dto.ErroDTO;
import com.cooperado.assembleia.model.dto.VotoDTO;
import com.cooperado.assembleia.model.enums.SimNaoEnum;
import com.cooperado.assembleia.repository.VotoRepository;

@Service
public class VotoBusiness {
	
	@Autowired
	private VotoRepository repository;
	
	@Autowired
	private PautaBusiness pautaBusiness;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String VOTO_LIBERADO = "";

	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> votar(Voto voto) {
		
		VotoDTO votoDTO = repository.buscarPorAssociadoEPauta(voto.getAssociado().getId(), voto.getPauta().getId());
		
		if(Objects.nonNull(votoDTO) && votoDTO.getId() != null) {
			String mensagem = messageSource.getMessage("associado.ja.votou", null, LocaleContextHolder.getLocale());
			
			return ResponseEntity.badRequest().body(ErroDTO.buildMensagemErroSomenteUsuario(mensagem));
		}
		
		String mensagemLiberacaoPauta = verificarPauta(voto.getPauta().getId());
		if(!mensagemLiberacaoPauta.isEmpty()) {
			return ResponseEntity.badRequest().body(ErroDTO.buildMensagemErroSomenteUsuario(mensagemLiberacaoPauta));
		}
		
		return ResponseEntity.ok(repository.save(voto));
	}
	
	private String verificarPauta(Long idPauta) {
		Optional<Pauta> pautaOptional = pautaBusiness.findById(idPauta);
		
		if(!pautaOptional.isPresent()) {
			return messageSource.getMessage("pauta.nao.encontrada", null, LocaleContextHolder.getLocale());
		}
		
		Pauta pauta = pautaOptional.get();
		if(Objects.nonNull(pauta.getLiberarVotacao()) && pauta.getLiberarVotacao().equals(SimNaoEnum.SIM)) {
			Instant inicioVotacao = pauta.getDataHoraInicio().toInstant();
			Instant tempoAtual = Instant.now();
			Duration tempoDecorrido = Duration.between(inicioVotacao, tempoAtual);
			if(tempoDecorrido.getSeconds() < 60) {
				return VOTO_LIBERADO;
			}
			
			pautaBusiness.encerrarPauta(pauta);
			return messageSource.getMessage("tempo.votacao.ja.encerrado", null, LocaleContextHolder.getLocale());
		}
		
		return messageSource.getMessage("pauta.nao.liberada.votacao", null, LocaleContextHolder.getLocale());
	}
	
	public Long quantidadeVotosAFavor(Long idPauta) {
		return repository.quantidadeVotosAFavor(idPauta);
	}
	
	public Long quantidadeVotosContra(Long idPauta) {
		return repository.quantidadeVotosContra(idPauta);
	}
}
