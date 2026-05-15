package br.com.serratec.aula5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula5.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
}
