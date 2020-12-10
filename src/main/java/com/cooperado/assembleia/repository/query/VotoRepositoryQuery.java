package com.cooperado.assembleia.repository.query;

import com.cooperado.assembleia.model.Voto;
import com.cooperado.assembleia.model.dto.VotoDTO;

public interface VotoRepositoryQuery {

	public VotoDTO buscarPorAssociadoEPauta(Long idAssociado, Long idPauta);
}
