package br.com.serratec.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.api.dto.EnderecoResponseDTO;
import br.com.serratec.api.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("{cep}")
    public ResponseEntity<EnderecoResponseDTO> buscar(@PathVariable String cep) {
        return ResponseEntity.ok(service.buscarCep(cep));
    }

}
