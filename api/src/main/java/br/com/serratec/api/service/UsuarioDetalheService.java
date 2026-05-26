package br.com.serratec.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.api.exception.UsuarioException;
import br.com.serratec.api.model.Usuario;
import br.com.serratec.api.repository.UsuarioRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(username);
        System.out.println("UsuarioDetalheService:"+username);
        if (usuario != null) {
            return usuario;
        }
        throw new UsuarioException("Email não encontrado!");
    }

}
