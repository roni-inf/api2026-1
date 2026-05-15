package br.com.serratec.aula5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula5.model.Manutencao;
import br.com.serratec.aula5.repository.ManutencaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {
    @Autowired
    private ManutencaoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manutencao inserir(@Valid @RequestBody Manutencao manutencao) {
        return repository.save(manutencao);
    }

    @PutMapping("{id}")
    public ResponseEntity<Manutencao> alterar(@Valid @RequestBody Manutencao manutencao, @PathVariable Long id) {
        if (repository.existsById(id)) {
            manutencao.setId(id);
            return ResponseEntity.ok(repository.save(manutencao));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Manutencao> buscar(@PathVariable Long id) {
        Optional<Manutencao> manutencao = repository.findById(id);
        if (manutencao.isPresent()) {
            ResponseEntity.ok(manutencao.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Manutencao>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
