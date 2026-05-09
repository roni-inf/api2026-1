package br.com.serratec.aula2banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula2banco.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
