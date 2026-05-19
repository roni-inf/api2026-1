package br.com.serratec.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.api.model.UsuarioPerfil;
import br.com.serratec.api.model.UsuarioPerfilPK;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {
}
