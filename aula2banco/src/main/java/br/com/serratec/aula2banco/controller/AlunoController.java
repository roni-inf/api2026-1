package br.com.serratec.aula2banco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula2banco.entity.Aluno;
import br.com.serratec.aula2banco.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno inserir(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        Optional<Aluno> aluno = repository.findById(id);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        }
        return ResponseEntity.notFound().build();
    }

}
