package com.kommunityon.website.entities;

import java.io.Serializable;

import jakarta.persistence.Column;

public class SolicitacaoTagPK implements Serializable{
    @Column(name="ID_SOLICITACAO")
    private Long solicitacaoId;

    @Column(name="ID_TAG")
    private Long tagId;

    
    //Getters e Setters
    public Long getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(Long solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTag(Long tagId) {
        this.tagId = tagId;
    }

}
