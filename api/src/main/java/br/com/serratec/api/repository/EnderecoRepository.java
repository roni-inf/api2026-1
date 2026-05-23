package br.com.serratec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);
}
