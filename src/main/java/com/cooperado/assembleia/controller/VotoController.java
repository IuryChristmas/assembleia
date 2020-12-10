package com.cooperado.assembleia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperado.assembleia.business.VotoBusiness;
import com.cooperado.assembleia.model.Voto;

@RestController
@RequestMapping("/votar")
public class VotoController {

	@Autowired
	private VotoBusiness business;
	
	@PostMapping
	public ResponseEntity<?> votar(@RequestBody Voto voto) {
		return business.votar(voto);
	}
}
