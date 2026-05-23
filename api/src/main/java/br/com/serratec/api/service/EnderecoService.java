package br.com.serratec.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.api.dto.EnderecoResponseDTO;
import br.com.serratec.api.exception.EnderecoException;
import br.com.serratec.api.model.Endereco;
import br.com.serratec.api.model.Usuario;
import br.com.serratec.api.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public EnderecoResponseDTO buscarCep(String cep) {
        Endereco enderecoBanco = repository.findByCep(cep);
        if (enderecoBanco != null) {
            return new EnderecoResponseDTO(enderecoBanco);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            Endereco enderecoViaCep = restTemplate.getForObject(url, Endereco.class);
            if (enderecoViaCep != null) {
                enderecoViaCep.setCep(enderecoViaCep.getCep().replaceAll("-", ""));
                return inserir(enderecoViaCep);
            }else{
                throw new EnderecoException("Cep não encontrado!");
            }
        }
    }

    private EnderecoResponseDTO inserir(Endereco enderecoViaCep) {
        return new EnderecoResponseDTO(repository.save(enderecoViaCep));
    }
}
