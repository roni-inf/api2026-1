package br.com.serratec.aula3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula3.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
