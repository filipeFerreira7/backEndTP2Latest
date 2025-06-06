package br.unitins.tp2.repository;

import br.unitins.tp2.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public Estado findBySigla(String sigla) {
        return find("SELECT e FROM Estado e WHERE e.sigla = ?1 ", sigla).firstResult();
    }

    public PanacheQuery<Estado> findByNome(String nome){
    return find ("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%");
    }

}