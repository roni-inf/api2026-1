package br.com.serratec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.api.model.Foto;
import br.com.serratec.api.model.Funcionario;

import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    Optional<Foto> findByFuncionario(Funcionario funcionario);

    

}
