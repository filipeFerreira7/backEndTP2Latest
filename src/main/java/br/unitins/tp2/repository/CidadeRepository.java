package br.unitins.tp2.repository;

import br.unitins.tp2.model.Cidade;
import br.unitins.tp2.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade> {

    public Cidade findById(Long id) {
        return find("SELECT c FROM Cidade c WHERE c.id = ?1 ", id).firstResult();
    }

    public List<Cidade> findByNome(String nome){
    return find ("SELECT c FROM Cidade c WHERE c.nome LIKE ?1", "%" + nome + "%").list();
    }

    public List<Cidade> findByEstado(Estado estado) {
        return find("SELECT c FROM Cidade c WHERE c.estado.id = ?1", estado.getId()).list();
    }


}