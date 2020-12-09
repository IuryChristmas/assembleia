package com.cooperado.assembleia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperado.assembleia.business.PautaBusiness;
import com.cooperado.assembleia.model.Pauta;

@RestController
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	private PautaBusiness business;
	
	@PostMapping("/criar")
	public ResponseEntity<Pauta> criar(@RequestBody Pauta pauta) {
		return ResponseEntity.ok(business.salvar(pauta));
	}

}
