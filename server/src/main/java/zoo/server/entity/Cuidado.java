
package zoo.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import zoo.server.utils.enums.FrequenciaCuidado;

@Entity
@Table(name = "tb_cuidados ")
public class Cuidado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
  
    @Column(name = "nome_cuidado")
    private String nomeCuidado;

    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private FrequenciaCuidado frequencia;
    
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public Cuidado() {}

    public Cuidado(String nomeCuidado, String descricao, FrequenciaCuidado frequencia, Animal animal) {
        this.nomeCuidado = nomeCuidado;
        this.descricao = descricao;
        this.frequencia = frequencia;
        this.animal = animal;
    }

    public Long getId() {
        return Id;
    }
    

    public String getNomeCuidado() {
        return nomeCuidado;
    }

    public void setNomeCuidado(String nomeCuidado) {
        this.nomeCuidado = nomeCuidado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public FrequenciaCuidado getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(FrequenciaCuidado frequencia) {
        this.frequencia = frequencia;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}
