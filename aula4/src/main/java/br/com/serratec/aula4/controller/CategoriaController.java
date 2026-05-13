package br.com.serratec.aula4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula4.model.Categoria;
import br.com.serratec.aula4.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria inserir(@Valid @RequestBody Categoria categoria) {
        return repository.save(categoria);
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Categoria> inserir(@Valid @RequestBody List<Categoria> categorias) {
        return repository.saveAll(categorias);
    }

    @GetMapping
    public List<Categoria> listar(){
        return repository.findAll();
    }

}
