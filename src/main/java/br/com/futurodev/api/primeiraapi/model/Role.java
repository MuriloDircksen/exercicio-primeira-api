/*package br.com.futurodev.api.primeiraapi.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeRole;*//*papel a ser dado, tipo ROLE_ADMIN, ROLE_GERENTE, ROLE_CAIXA*//*

    @Override
    public String getAuthority() {

        return this.nomeRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }
}*/
