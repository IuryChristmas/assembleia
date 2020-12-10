package com.cooperado.assembleia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperado.assembleia.model.Associado;
import com.cooperado.assembleia.repository.query.AssociadoRepositoryQuery;

public interface AssociadoRepository extends JpaRepository<Associado, Long>, AssociadoRepositoryQuery {

}
