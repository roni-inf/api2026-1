package br.com.serratec.api.controller;

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

import br.com.serratec.api.model.Perfil;
import br.com.serratec.api.service.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Perfil inserir(@RequestBody Perfil perfil) {
        return service.inserir(perfil);
    }

    // Fazer o buscar por id
    @GetMapping("{id}")
    public ResponseEntity<Perfil> buscar(@PathVariable Long id) {
        Optional<Perfil> perfil = service.buscar(id);
        if (perfil.isPresent()) {
            return ResponseEntity.ok(perfil.get());
        }
        return ResponseEntity.notFound().build();
    }
}
