package br.unitins.tp2.dto;

import br.unitins.tp2.model.Cidade;

public record CidadeDTOResponse(
        Long id,
        String nome,
        EstadoDTOResponse estadoDTO
) {
    public static CidadeDTOResponse valueOf(Cidade cidade) {
        return new CidadeDTOResponse(cidade.getId(), cidade.getNome(), EstadoDTOResponse.valueOf(cidade.getEstado()));
    }
}
