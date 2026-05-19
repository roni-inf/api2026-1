package br.com.serratec.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.api.service.UsuarioService;
import java.util.*;

import br.com.serratec.api.dto.UsuarioRequestDTO;
import br.com.serratec.api.dto.UsuarioResponseDTO;
import br.com.serratec.api.model.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public Usuario inserir(@RequestBody Usuario usuario) {
    //     return service.inserir(usuario);
    // }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> inserir(@RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.created(null).body(service.inserir(dto));
    }
}
