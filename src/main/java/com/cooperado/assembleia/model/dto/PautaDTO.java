package com.cooperado.assembleia.model.dto;

import java.util.Date;

import com.cooperado.assembleia.model.enums.SimNaoEnum;
import com.cooperado.assembleia.model.enums.Status;

public class PautaDTO {

	private Long id;
	
	private String titulo;
	
	private Date dataCriacao;
	
	private Status status; 
	
	private SimNaoEnum liberarVotacao;

	private Date dataHoraInicio;
	
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
	
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}
	
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
}
