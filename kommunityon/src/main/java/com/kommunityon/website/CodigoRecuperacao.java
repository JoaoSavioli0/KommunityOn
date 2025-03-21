package com.kommunityon.website;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CodigoRecuperacao {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String codigo;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO", nullable=false)
    private Usuario usuario;

    @Column(insertable = false, updatable = false)
    private LocalDateTime emissao;

    
    //Getters e Setters
    public LocalDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
