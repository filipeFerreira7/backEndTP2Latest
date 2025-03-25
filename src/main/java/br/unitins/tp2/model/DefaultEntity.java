package br.unitins.tp2.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass // diz que todos os atributos da herança vão ser colocados na tabela principal, não terei uma tabela 'DefaultEntity'
@Data
public class DefaultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}


