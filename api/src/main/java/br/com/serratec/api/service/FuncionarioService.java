package br.com.serratec.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.api.model.Funcionario;
import br.com.serratec.api.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> listar(){
        return repository.findAll();
    }

    public Page<Funcionario> listarPorPagina(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<Funcionario> listarPorFaixaSalarial(Pageable pageable, Double valorMinimo, Double valorMaximo){
        return repository.findBySalarioBetween(pageable, valorMinimo, valorMaximo);
    }

    public Page<Funcionario> listarPorNome(Pageable pageable, String nome){
        return repository.findByNomeContaining(pageable, nome);
    }

}
