package br.com.serratec.api.dto;

import br.com.serratec.api.model.Endereco;

public record EnderecoRequestDTO(String cep, String logradouro, String bairro, String localidade, String uf) {

    public EnderecoRequestDTO(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf());
    }

}
