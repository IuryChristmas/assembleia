package com.cooperado.assembleia.business;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cooperado.assembleia.model.Associado;
import com.cooperado.assembleia.model.dto.ErroDTO;
import com.cooperado.assembleia.model.dto.UserDTO;
import com.cooperado.assembleia.repository.AssociadoRepository;

@Service
public class AssociadoBusiness {

	@Autowired
	private AssociadoRepository repository;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String URL = "https://user-info.herokuapp.com/users/{cpf}";
	private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
	
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> salvar(Associado associado) throws NoSuchMessageException, Exception {
		
		if(!this.isCpfValido(associado.getCpf())) {
			String mensagem = messageSource.getMessage("cpf.invalido", null, LocaleContextHolder.getLocale());
			return ResponseEntity.badRequest().body(ErroDTO.buildMensagemErroSomenteUsuario(mensagem));
		}
		
		return ResponseEntity.ok(repository.save(associado));
	}
	
	private boolean isCpfValido(String cpf) {
		boolean isValido = false;
		
		RestTemplate restTemplate = new RestTemplate();
		UserDTO userDTO = restTemplate.getForObject(URL.replace("{cpf}", cpf), UserDTO.class);
		
		if(Objects.nonNull(userDTO) && userDTO.getStatus().equals(ABLE_TO_VOTE)) {
			isValido = true;
		}
		
		return isValido;
	}

	public void deleteById(Long id) {
		repository.deleteById(id);		
	}
	
}
