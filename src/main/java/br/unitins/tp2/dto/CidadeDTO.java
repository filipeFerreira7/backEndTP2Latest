package br.unitins.tp2.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;


public record CidadeDTO(
    @NotBlank(message="O nome da cidade deve ser preenchido")
    @Size(max = 60,message =  "O nome deve conter no máximo 60 caracteres")
    String nome,

    @NotNull(message = "O Estado precisa ser referenciado e o valor mínimo é 1")
    @Min(1)
    Long idEstado) {}