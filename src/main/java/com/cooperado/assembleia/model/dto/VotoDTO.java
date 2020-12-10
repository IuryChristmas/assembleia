package com.cooperado.assembleia.model.dto;

import com.cooperado.assembleia.model.Associado;
import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.model.enums.SimNaoEnum;

public class VotoDTO {

	private Long id;

	private SimNaoEnum votoEnum;
	
    private Pauta pauta;
	
	private Associado associado;
	
	public VotoDTO() {}
	
	public VotoDTO(Long id) {
		this.id = id;
	}
	
	public VotoDTO(Long id, SimNaoEnum votoEnum) {
		this.id = id;
		this.votoEnum = votoEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimNaoEnum getVotoEnum() {
		return votoEnum;
	}

	public void setVotoEnum(SimNaoEnum votoEnum) {
		this.votoEnum = votoEnum;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
}
