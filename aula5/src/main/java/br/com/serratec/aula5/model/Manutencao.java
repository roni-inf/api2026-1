package br.com.serratec.aula5.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;


    @ManyToMany
    @JoinTable(name = "manutencao_servico",
               joinColumns = @JoinColumn(name="id_manutencao"),
               inverseJoinColumns = @JoinColumn(name="id_servico")
    )
    private List<Servico> servicos = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    @PrePersist
    public void prePersistDataEntrada() {
        dataEntrada = LocalDate.now();
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    
}
