package br.unitins.tp2.service;

import java.util.List;

import br.unitins.tp2.dto.EstadoDTO;
import br.unitins.tp2.dto.EstadoDTOResponse;
import br.unitins.tp2.model.Estado;
import br.unitins.tp2.model.Regiao;
import br.unitins.tp2.repository.EstadoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public Estado create(EstadoDTO estado) {
        Estado novoEstado = new Estado();
        novoEstado.setNome(estado.nome());
        novoEstado.setSigla(estado.sigla());
       
        // selecionar uma regiao
        novoEstado.setRegiao(Regiao.CENTRO_OESTE);

        // realizando inclusao
        estadoRepository.persist(novoEstado);

        return novoEstado;
    }

    @Override
    @Transactional
    public void update(long id, Estado estado) {
        Estado edicaoEstado = estadoRepository.findById(id);

        edicaoEstado.setNome(estado.getNome());
        edicaoEstado.setSigla(estado.getSigla());
    }

    @Override
    @Transactional
    public void delete(long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public Estado findById(long id) {
        return estadoRepository.findById(id);
    }

    @Override
    public Estado findBySigla(String sigla) {
        return estadoRepository.findBySigla(sigla);
    }

    @Override
    public List<Estado> findAll(Integer page, Integer pageSize) {
        PanacheQuery<Estado> query = null;
        if(page == null || pageSize == null)
            query = estadoRepository.findAll();
        else
            query = estadoRepository.findAll().page(page, pageSize);

        return query.list();
    }


    @Override
    public List<EstadoDTOResponse> findByNome(String nome, Integer page, Integer pageSize) {
        List<Estado> estadosList = estadoRepository.findByNome(nome).page(page,pageSize).list();

        return estadosList.stream().map(e -> EstadoDTOResponse.valueOf(e)).toList();
    }
    @Override
    public List<EstadoDTOResponse> findByNome(String nome) {
      List<Estado> list = estadoRepository.findByNome(nome).list();

      return list.stream().map(e -> EstadoDTOResponse.valueOf(e)).toList();
    }

    @Override
    public long count() {
        return estadoRepository.findAll().count();
    }

    @Override
    public long count(String nome) {
        return estadoRepository.findByNome(nome).count();
    }

    @Override
    public long countFiltrados(String nome) {
        return estadoRepository.findByNome(nome).count();
    }
    
}
