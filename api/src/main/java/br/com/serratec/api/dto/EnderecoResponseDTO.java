package br.com.serratec.api.dto;

import br.com.serratec.api.model.Endereco;

public record EnderecoResponseDTO(String cep, String logradouro, String bairro, String localidade, String uf) {

        public EnderecoResponseDTO(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf());
    }

}
