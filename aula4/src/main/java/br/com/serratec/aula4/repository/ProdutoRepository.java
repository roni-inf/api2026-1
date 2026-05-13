package br.com.serratec.aula4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula4.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
