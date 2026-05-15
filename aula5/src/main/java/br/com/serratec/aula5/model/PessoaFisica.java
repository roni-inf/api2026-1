package br.com.serratec.aula5.model;

import jakarta.persistence.Entity;

@Entity
public class PessoaFisica extends Fornecedor {

    private String ci;
    private String orgaoExpedidor;
    private String cpf;

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
