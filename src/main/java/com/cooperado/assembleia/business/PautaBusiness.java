package com.cooperado.assembleia.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.repository.PautaRepository;

@Service
public class PautaBusiness {
	
	@Autowired
	private PautaRepository repository;
	
	@Transactional(rollbackFor = Exception.class)
	public Pauta salvar(Pauta pauta) {
		pauta.setDataCriacao(new Date());
		
		return repository.save(pauta);
	}

}
