package com.cooperado.assembleia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperado.assembleia.model.Voto;
import com.cooperado.assembleia.repository.query.VotoRepositoryQuery;

public interface VotoRepository extends JpaRepository<Voto, Long>, VotoRepositoryQuery  {

}
