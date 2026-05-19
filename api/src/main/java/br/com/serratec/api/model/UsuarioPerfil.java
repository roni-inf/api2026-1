package br.com.serratec.api.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioPerfil {

    @EmbeddedId
    private UsuarioPerfilPK id = new UsuarioPerfilPK();

    private LocalDate dataCriacao;
    private Boolean ativo;

    public UsuarioPerfil() {
    }

    public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao, Boolean ativo) {
        id.setUsuario(usuario);
        id.setPerfil(perfil);
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
    }

    public UsuarioPerfilPK getId() {
        return id;
    }

    public void setId(UsuarioPerfilPK id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        id.setUsuario(usuario);
    }

    public Usuario getUsuario() {
        return id.getUsuario();
    }

    public void setPerfil(Perfil perfil) {
        id.setPerfil(perfil);
    }

    public Perfil getPerfil() {
        return id.getPerfil();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
