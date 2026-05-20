package br.com.serratec.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.api.dto.UsuarioRequestDTO;
import br.com.serratec.api.dto.UsuarioResponseDTO;
import br.com.serratec.api.exception.UsuarioException;
import br.com.serratec.api.model.Usuario;
import br.com.serratec.api.model.UsuarioPerfil;
import br.com.serratec.api.repository.PerfilRepository;
import br.com.serratec.api.repository.UsuarioPerfilRepository;
import br.com.serratec.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private BCryptPasswordEncoder criptografar;

    UsuarioService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
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

        for (UsuarioPerfil up : dto.getUsuarioPerfis()) {
            up.setUsuario(usuarioSalvo);
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()).get());
            up.setDataCriacao(LocalDate.now());
            up.setAtivo(true);
        }

        // List<UsuarioPerfil> lista = dto.getUsuarioPerfis().stream().map(up -> new
        // UsuarioPerfil(usuarioSalvo,
        // perfilService.buscar(up.getPerfil().getId()).get(),
        // LocalDate.now(), true) {
        // }).collect(Collectors.toList());

        usuarioPerfilRepository.saveAll(dto.getUsuarioPerfis());

        return new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }
}
