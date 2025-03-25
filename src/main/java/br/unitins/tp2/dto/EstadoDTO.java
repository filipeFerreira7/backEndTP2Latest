package br.unitins.tp2.dto;

import br.unitins.tp2.model.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record EstadoDTO(
        @NotBlank(message = "O nome do estado deve ser preenchido")
        @Size(max = 60, message = "O nome deve possuir no máximo 60 caracteres")
        String nome,

        @NotBlank(message = "O campo sigla é obrigatório")
        @Size(min = 2, max = 2, message = "A sigla deve possuir dois caracteres")
        String sigla

) {
}