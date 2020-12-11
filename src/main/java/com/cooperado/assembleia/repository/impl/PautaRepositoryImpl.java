package com.cooperado.assembleia.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.model.dto.PautaDTO;
import com.cooperado.assembleia.repository.query.PautaRepositoryQuery;

public class PautaRepositoryImpl implements PautaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pauta> buscarPorFiltro(PautaDTO filtro) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT pt FROM pautas pt");
		this.buildWhere(sql, filtro);
		
		Query query = manager.createQuery(sql.toString());
		this.addParameters(query, filtro);
		
		return query.getResultList();
	}
	
	private void buildWhere(StringBuilder sql, PautaDTO filtro) {
		sql.append(" WHERE 1=1");
		
		if(filtro != null) {
			if(filtro.getId() != null) {
				sql.append(" AND pt.id = :id");
			}
			
			if(filtro.getTitulo() != null && !filtro.getTitulo().isEmpty()) {
				sql.append(" AND pt.titulo like :titulo");
			}
			
			if(filtro.getStatus() != null) {
				sql.append(" AND pt.status = :status");
			}	
		}
		
	}
	
	private void addParameters(Query query, PautaDTO filtro) {
		if(filtro != null) {
			if(filtro.getId() != null) {
				query.setParameter("id", filtro.getId());
			}
			
			if(filtro.getTitulo() != null && !filtro.getTitulo().isEmpty()) {
				query.setParameter("titulo", filtro.getTitulo());
			}
			
			if(filtro.getStatus() != null) {
				query.setParameter("status", filtro.getStatus());
			}
		}
	}

}
