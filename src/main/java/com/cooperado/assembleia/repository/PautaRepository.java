package com.cooperado.assembleia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.repository.query.PautaRepositoryQuery;

public interface PautaRepository extends JpaRepository<Pauta, Long>, PautaRepositoryQuery {

}
