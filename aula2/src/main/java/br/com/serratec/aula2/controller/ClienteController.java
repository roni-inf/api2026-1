package br.com.serratec.aula2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula2.model.Cliente;

@RestController
@RequestMapping("/clientes") // http://enderecoip:8080/clientes
public class ClienteController {
    private static List<Cliente> clientes = new ArrayList<>();

    static {
        clientes.add(new Cliente(1L, "Ana", "ana@gmail.com"));
        clientes.add(new Cliente(2L, "Marcelo", "marcelo@gmail.com"));
        clientes.add(new Cliente(3L, "Carlos", "carlos@gmail.com"));
    }

    @GetMapping
    public List<Cliente> listar() {
        return clientes;
    }

    @GetMapping("{id}")
    public Cliente buscar(@PathVariable Long id) {
        // for (int i = 0; i < clientes.size(); i++) {
        // if (clientes.get(i).getId().equals(id)) {
        // return clientes.get(i);
        // }
        // }
        // return null;
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserir(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    @DeleteMapping("{id}")
    public void apagar(@PathVariable Long id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                clientes.remove(i);
            }
        }
    }

    @PutMapping("{id}")
    public Cliente alterar(@RequestBody Cliente cliente, @PathVariable Long id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                Cliente c = new Cliente(id, cliente.getNome(), cliente.getEmail());
                clientes.set(i, c);
                return c;
            }
        }
        return null;
    }

}
