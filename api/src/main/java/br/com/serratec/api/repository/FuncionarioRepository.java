package br.com.serratec.api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.api.model.Funcionario;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    // @Query(value = "select * from funcionario where salario between :valorMinimo
    // and :valorMaximo", nativeQuery = true)
    // Page<Funcionario> buscarSalarioPorFaixa(Pageable pageable, Double
    // valorMinimo, Double valorMaximo);

    Page<Funcionario> findBySalarioBetween(Pageable pageable, Double valorMinimo, Double valorMaximo);
    // @Query(value = "select * from funcionario where nome ilike CONCAT(
    // '%',:nome,'%') ", nativeQuery = true)
    // Page<Funcionario> buscarPorNome(Pageable pageable, String nome);

    Page<Funcionario> findByNomeContaining(Pageable pageable, String nome);

}
