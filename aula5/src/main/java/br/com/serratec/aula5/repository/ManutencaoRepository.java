package br.com.serratec.aula5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula5.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    
}
