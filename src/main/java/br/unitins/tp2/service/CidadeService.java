package br.unitins.tp2.service;

import java.util.List;

import br.unitins.tp2.dto.CidadeDTO;
import br.unitins.tp2.dto.CidadeDTOResponse;
import br.unitins.tp2.model.Cidade;

public interface CidadeService {

    Cidade create(CidadeDTO dto);
    void update(long id, CidadeDTO dto);
    void delete(long id);
    Cidade findById(long id);
    List<CidadeDTOResponse> findAll(Integer page, Integer pageSize);
    List<Cidade> findByNome(String nome, Integer page, Integer pageSize);
    List<Cidade> findByNome(String nome);
    long count();
    long countByNome(String nome);
}
