package br.unitins.tp2.dto;

import br.unitins.tp2.model.Cidade;
import br.unitins.tp2.model.Estado;

public record EstadoDTOResponse(
        Long id,
        String nome,
        String sigla
) {
    public static EstadoDTOResponse valueOf(Estado estado) {
        return new EstadoDTOResponse(
                estado.getId(), estado.getNome(), estado.getSigla()
        );
    }
}
