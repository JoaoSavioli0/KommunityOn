package com.kommunityon.website.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="recompensa_usuario")
public class RecompensaUsuario {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_recompensa")
    private Recompensa recompensa;

    private boolean ativado = false;

    public RecompensaUsuario(Usuario usuario, Recompensa recompensa){
        this.usuario = usuario;
        this.recompensa = recompensa;
    }

    public RecompensaUsuario(){

    }


    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }
    
}
