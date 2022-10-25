package br.com.futurodev.api.primeiraapi.input;

import br.com.futurodev.api.primeiraapi.dto.TelefoneRepresetationModel;
import br.com.futurodev.api.primeiraapi.model.TelefoneModel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioInput {

    private Long id;
    private String nome;
    private String login;
    private String senha;


    private List<TelefoneRepresetationModel> telefones= new ArrayList<TelefoneRepresetationModel>();

    public List<TelefoneRepresetationModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneRepresetationModel> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
