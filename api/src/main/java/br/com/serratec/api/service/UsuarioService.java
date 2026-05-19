package br.com.serratec.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.api.dto.UsuarioRequestDTO;
import br.com.serratec.api.dto.UsuarioResponseDTO;
import br.com.serratec.api.exception.UsuarioException;
import br.com.serratec.api.model.Usuario;
import br.com.serratec.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder criptografar;

    public List<UsuarioResponseDTO> listarTodos() {

        // List<Usuario> usuarios = repository.findAll();
        // List<UsuarioResponseDTO> dto = new ArrayList<>();

        // for (Usuario usuario : usuarios) {
        // dto.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(),
        // usuario.getEmail()));
        // }
        // return dto;

        return repository.findAll().stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {
        Usuario usuarioBanco = repository.findByEmail(dto.getEmail());

        if (usuarioBanco != null) {
            throw new UsuarioException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(criptografar.encode(dto.getSenha()));

        Usuario usuarioSalvo = repository.save(usuario);
        return new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }
}
