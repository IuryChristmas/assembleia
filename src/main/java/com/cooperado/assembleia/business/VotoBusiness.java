package com.cooperado.assembleia.business;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cooperado.assembleia.model.Voto;
import com.cooperado.assembleia.model.dto.ErroDTO;
import com.cooperado.assembleia.model.dto.VotoDTO;
import com.cooperado.assembleia.repository.VotoRepository;

@Service
public class VotoBusiness {
	
	@Autowired
	private VotoRepository repository;
	
	@Autowired
	private MessageSource messageSource;

	public ResponseEntity<?> votar(Voto voto) {
		
		VotoDTO votoDTO = repository.buscarPorAssociadoEPauta(voto.getAssociado().getId(), voto.getPauta().getId());
		
		if(Objects.nonNull(votoDTO) && votoDTO.getId() != null) {
			String mensagem = messageSource.getMessage("associado.ja.votou", null, LocaleContextHolder.getLocale());
			
			return ResponseEntity.badRequest().body(ErroDTO.buildMensagemErroSomenteUsuario(mensagem));
		}
		
		return ResponseEntity.ok(repository.save(voto));
	}
}
