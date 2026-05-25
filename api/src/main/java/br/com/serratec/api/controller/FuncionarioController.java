package br.com.serratec.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.api.dto.FuncionarioResponseDTO;
import br.com.serratec.api.model.Foto;
import br.com.serratec.api.model.Funcionario;
import br.com.serratec.api.service.FotoService;
import br.com.serratec.api.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @Autowired
    private FotoService fotoService;

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
            @RequestParam(defaultValue = "1000") Double valorMinimo, @RequestParam Double valorMaximo) {
        return service.listarPorFaixaSalarial(pageable, valorMinimo, valorMaximo);
    }

    @GetMapping("/nome")
    public Page<Funcionario> listarPorNome(Pageable pageable, @RequestParam(defaultValue = "") String nome) {
        return service.listarPorNome(pageable, nome);
    }

    @GetMapping("{id}/foto")
    public ResponseEntity<byte[]> buscarPorFoto(@PathVariable UUID id) {
        Foto foto = fotoService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", foto.getTipo());
        headers.add("Content-length", String.valueOf(foto.getDados().length));
        return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public FuncionarioResponseDTO inserir(@RequestPart Funcionario funcionario, @RequestPart MultipartFile file)
            throws IOException {
        return service.inserir(funcionario, file);
    }

}
