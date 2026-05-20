package br.com.serratec.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.api.model.Funcionario;
import br.com.serratec.api.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @GetMapping
    public List<Funcionario> listar() {
        return service.listar();
    }

    @GetMapping("/paginacao")
    public Page<Funcionario> listarPorPagina(
            @PageableDefault(size = 6, page = 0, sort = "dataNascimento", direction = Direction.ASC) Pageable pageable) {
        return service.listarPorPagina(pageable);
    }

    @GetMapping("/faixasalarial")
    public Page<Funcionario> listarPorFaixaSalarial(Pageable pageable,
        @RequestParam(defaultValue = "1000") Double valorMinimo,@RequestParam Double valorMaximo){
         return service.listarPorFaixaSalarial(pageable, valorMinimo, valorMaximo);
    }


    @GetMapping("/nome")
    public Page<Funcionario> listarPorNome(Pageable pageable, @RequestParam(defaultValue = "") String nome){
         return service.listarPorNome(pageable, nome);
    }
}
