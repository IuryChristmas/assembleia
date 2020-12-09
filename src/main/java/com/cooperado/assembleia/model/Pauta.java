package com.cooperado.assembleia.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cooperado.assembleia.model.enums.SimNaoEnum;
import com.cooperado.assembleia.model.enums.Status;

@Entity(name = "pautas")
public class Pauta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String titulo;
	
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private Status status; 
	
	@Column(name = "liberar_votacao")
	private SimNaoEnum liberarVotacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public SimNaoEnum getLiberarVotacao() {
		return liberarVotacao;
	}

	public void setLiberarVotacao(SimNaoEnum liberarVotacao) {
		this.liberarVotacao = liberarVotacao;
	}
	
}
