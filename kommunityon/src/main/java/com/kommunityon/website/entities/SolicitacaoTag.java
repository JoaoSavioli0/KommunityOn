package com.kommunityon.website.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SOLICITACAO_TAG")
@IdClass(SolicitacaoTagPK.class)
public class SolicitacaoTag {
    @Id
    @Column(name = "ID_SOLICITACAO")
    private Long solicitacaoId;

    @Id
    @Column(name = "ID_TAG")
    private Long tagId; // Manter como Long para corresponder a SolicitaçãoTagPK

    @ManyToOne
    @JoinColumn(name = "ID_TAG", insertable = false, updatable = false)
    private Tag tag; // Relacionamento separado

    public SolicitacaoTag(Long solicitacaoId, Tag tag) {
        this.solicitacaoId = solicitacaoId;
        this.tag = tag;
        this.tagId = tag.getId();
    }

    public SolicitacaoTag() {
    }

    // Getters e Setters
    public Long getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(Long solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

}
