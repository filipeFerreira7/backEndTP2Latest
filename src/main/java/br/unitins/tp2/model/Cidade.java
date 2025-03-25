package br.unitins.tp2.model;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Cidade extends DefaultEntity {

        @Column
        private String nome;

        @ManyToOne
        @JoinColumn(name="id_estado")
        private Estado estado;

}
