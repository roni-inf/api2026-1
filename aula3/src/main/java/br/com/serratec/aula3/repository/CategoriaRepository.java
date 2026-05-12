package br.com.serratec.aula3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula3.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
