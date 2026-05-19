package br.com.serratec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.api.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
