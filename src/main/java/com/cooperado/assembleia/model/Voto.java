package com.cooperado.assembleia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cooperado.assembleia.model.enums.SimNaoEnum;

@Entity(name = "votos")
public class Voto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "voto_associado")
	@Enumerated(EnumType.ORDINAL)
	private SimNaoEnum votoEnum;
	
	@ManyToOne()
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private Pauta pauta;
	
	@ManyToOne
	@JoinColumn(name = "id_associado", referencedColumnName = "id")
	private Associado associado;
	
	public Voto() {
	}

	public Voto(Long id) {
		this.id = id;
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
