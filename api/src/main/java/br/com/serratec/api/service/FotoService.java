package br.com.serratec.api.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.api.model.Foto;
import br.com.serratec.api.model.Funcionario;
import br.com.serratec.api.repository.FotoRepository;

@Service
public class FotoService {

    @Autowired
    private FotoRepository repository;

    public Foto inserir(Funcionario funcionario, MultipartFile file) throws IOException {
        Foto foto = new Foto(null, file.getBytes(), file.getContentType(), file.getName(),funcionario);
        return repository.save(foto);
    }

    public Foto buscar(UUID id) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        return repository.findByFuncionario(funcionario).get();
    }
}
