package com.cooperado.assembleia.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cooperado.assembleia.model.dto.VotoDTO;
import com.cooperado.assembleia.model.enums.SimNaoEnum;
import com.cooperado.assembleia.repository.query.VotoRepositoryQuery;

public class VotoRepositoryImpl implements VotoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public VotoDTO buscarPorAssociadoEPauta(Long idAssociado, Long idPauta) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new com.cooperado.assembleia.model.dto.VotoDTO(vt.id)")
			.append(" FROM votos vt")
			.append(" WHERE vt.associado.id = :idAssociado AND vt.pauta.id = :idPauta");
		
		Query query = manager.createQuery(sql.toString());
		query.setParameter("idAssociado", idAssociado)
			.setParameter("idPauta", idPauta);
		try {
			return (VotoDTO) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public Long quantidadeVotosAFavor(Long idPauta) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(vt.id) FROM votos vt")
			.append(" WHERE vt.pauta.id = :idPauta AND vt.votoEnum = :votoEnum");
		
		Query query = manager.createQuery(sql.toString());
		query.setParameter("idPauta", idPauta)
			.setParameter("votoEnum", SimNaoEnum.SIM);
		
		return (Long) query.getSingleResult();
	}

	@Override
	public Long quantidadeVotosContra(Long idPauta) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(vt.id) FROM votos vt")
			.append(" WHERE vt.pauta.id = :idPauta AND vt.votoEnum = :votoEnum");
		
		Query query = manager.createQuery(sql.toString());
		query.setParameter("idPauta", idPauta)
			.setParameter("votoEnum", SimNaoEnum.NAO);
		
		return (Long) query.getSingleResult();
	}

}
