package br.com.serratec.aula1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Controlador - Requisições HTTP - Resposta HTTP
@RestController
// Criação do recurso ou end-point
@RequestMapping("/aulas")
public class Teste {

    @GetMapping
    public String mensagem(@RequestParam String frase) {
        return frase.toUpperCase();
    }

    @GetMapping("texto")
    public String texto() {
        return "Aula de API Rest";
    }

}
