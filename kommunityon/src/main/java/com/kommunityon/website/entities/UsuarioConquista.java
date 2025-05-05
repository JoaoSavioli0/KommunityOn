package com.kommunityon.website.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO_CONQUISTA")
@IdClass(UsuarioConquistaPK.class)
public class UsuarioConquista {
    @Id
    @Column(name = "ID_USUARIO")
    private Long usuarioId;

    @Id
    @Column(name = "ID_CONQUISTA")
    private Long conquistaId; // Manter como Long para corresponder a SolicitaçãoTagPK

    public UsuarioConquista(Usuario usuario, Conquista conquista) {
        this.usuarioId = usuario.getId();
        this.conquistaId = conquista.getId();
    }

        // Construtor sem argumentos (necessário para o Hibernate)
    public UsuarioConquista() {
    }


    // Getters e Setters
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
