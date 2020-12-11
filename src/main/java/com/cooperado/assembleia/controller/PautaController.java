package com.cooperado.assembleia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperado.assembleia.business.PautaBusiness;
import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.model.dto.PautaDTO;

@RestController
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	private PautaBusiness business;
	
	@PostMapping("/criar")
	public ResponseEntity<Pauta> criar(@RequestBody Pauta pauta) {
		return ResponseEntity.ok(business.salvar(pauta));
	}

	@PutMapping("/iniciar-votacao-pauta/{id}")
	public ResponseEntity<?> iniciarVotacaoPauta(@PathVariable Long id) {
		return business.iniciarVotacaoParaPauta(id);
	}
	
	@GetMapping
	public List<Pauta> buscarPorFiltro(PautaDTO filtro) {
		return business.buscarTodos(filtro);
	}
}
