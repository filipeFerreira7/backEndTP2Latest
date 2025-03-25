package br.unitins.tp2.service;

import java.util.List;

import br.unitins.tp2.dto.CidadeDTO;
import br.unitins.tp2.dto.CidadeDTOResponse;
import br.unitins.tp2.model.Cidade;
import br.unitins.tp2.repository.CidadeRepository;
import br.unitins.tp2.repository.EstadoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    CidadeRepository cidadeRepository;
    @Inject
    EstadoRepository estadoRepository;
    @Inject
    EstadoService estadoService;


    @Override
    public Cidade findById(long id) {
        return cidadeRepository.findById(id);
    }


    @Override
    @Transactional
    public Cidade create(CidadeDTO dto) {


        Cidade cidade = new Cidade();
        if (estadoService.findById(dto.idEstado()) == null) {
            throw new IllegalArgumentException("Estado Não encontrado");
        }
        cidade.setEstado(estadoService.findById(dto.idEstado()));
        cidade.setNome(dto.nome());

        cidadeRepository.persist(cidade);

        return cidade;
    }

    @Override
    @Transactional
    public void update(long id, CidadeDTO dto) {
        Cidade c = cidadeRepository.findById(id);
        c.setNome(dto.nome());
        c.setEstado(estadoRepository.findById(dto.idEstado()));

        cidadeRepository.persist(c);
    }

    @Override
    @Transactional
    public void delete(long id) {
        cidadeRepository.deleteById(id);
    }


    @Override
    public List<CidadeDTOResponse> findAll(Integer page, Integer pageSize) {
        PanacheQuery<Cidade> query = cidadeRepository.findAll();

        if (page != null && pageSize != null) {
            query.page(page, pageSize);
        }

        return query.list()
                .stream()
                .map(CidadeDTOResponse::valueOf)
                .toList();
    }


    @Override
    public List<Cidade> findByNome(String nome, Integer page, Integer pageSize) {
        return cidadeRepository.findByNome(nome);
    }


    @Override
    public List<Cidade> findByNome(String nome) {
        return cidadeRepository.findByNome(nome);
    }


    @Override
    public long count() {
        return cidadeRepository.findAll().count();
    }

    @Override
    public long countByNome(String nome) {
        return cidadeRepository.findByNome(nome).stream().count();
    }

}
