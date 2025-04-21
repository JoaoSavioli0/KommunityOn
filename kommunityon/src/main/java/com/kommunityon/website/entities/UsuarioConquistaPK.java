package com.kommunityon.website.entities;

import java.io.Serializable;

import jakarta.persistence.Column;

public class UsuarioConquistaPK implements Serializable{
    @Column(name="ID_USUARIO")
    private Long usuarioId;

    @Column(name="ID_CONQUISTA")
    private Long conquistaId;


    //Getters e Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getConquistaId() {
        return conquistaId;
    }

    public void setConquistaId(Long conquistaId) {
        this.conquistaId = conquistaId;
    }
}
