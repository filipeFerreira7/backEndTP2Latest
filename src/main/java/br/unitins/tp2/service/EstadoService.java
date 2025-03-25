package br.unitins.tp2.service;

import java.util.List;

import br.unitins.tp2.dto.EstadoDTO;
import br.unitins.tp2.dto.EstadoDTOResponse;
import br.unitins.tp2.model.Estado;

public interface EstadoService {

    Estado create(EstadoDTO estado);
    void update(long id, Estado estado);
    void delete(long id);
    Estado findById(long id);
    Estado findBySigla(String sigla);
    List<Estado> findAll(Integer page, Integer pageSize);
    List<EstadoDTOResponse> findByNome(String nome, Integer page, Integer pageSize);
    List<EstadoDTOResponse> findByNome(String nome);
    long count();
    long count(String nome);
    long countFiltrados(String nome);


}
