package com.cooperado.assembleia.repository.query;

import java.util.List;

import com.cooperado.assembleia.model.Pauta;
import com.cooperado.assembleia.model.dto.PautaDTO;

public interface PautaRepositoryQuery {

	public List<Pauta> buscarPorFiltro(PautaDTO filtro);
}
