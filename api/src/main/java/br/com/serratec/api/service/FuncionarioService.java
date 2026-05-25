package br.com.serratec.api.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.api.dto.FuncionarioResponseDTO;
import br.com.serratec.api.model.Funcionario;
import br.com.serratec.api.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FotoService fotoService;

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Page<Funcionario> listarPorPagina(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Funcionario> listarPorFaixaSalarial(Pageable pageable, Double valorMinimo, Double valorMaximo) {
        return repository.findBySalarioBetween(pageable, valorMinimo, valorMaximo);
    }

    public Page<Funcionario> listarPorNome(Pageable pageable, String nome) {
        return repository.findByNomeContaining(pageable, nome);
    }

    public List<FuncionarioResponseDTO> listarComFoto() {
        return repository.findAll().stream().map(f -> adicionarUriFoto(f))
                .collect(Collectors.toList());
    }

    //Retorna um DTO com o link para abrir a foto que foi salva
    public FuncionarioResponseDTO adicionarUriFoto(Funcionario funcionario) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionarios/{id}/foto")
                .buildAndExpand(funcionario.getId()).toUri();

        FuncionarioResponseDTO dto = new FuncionarioResponseDTO(funcionario.getNome(), funcionario.getEmail(),
                uri.toString());
        return dto;
    }

    public FuncionarioResponseDTO buscar (UUID id){
       Optional<Funcionario> funcionario =  repository.findById(id);
       return adicionarUriFoto(funcionario.get());
    }


    @Transactional
    public FuncionarioResponseDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException{
        funcionario = repository.save(funcionario);
        fotoService.inserir(funcionario, file);        
        return adicionarUriFoto(funcionario);
    }
}
