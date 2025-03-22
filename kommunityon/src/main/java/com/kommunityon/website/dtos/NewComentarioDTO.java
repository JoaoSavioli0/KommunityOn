package com.kommunityon.website.dtos;

public class NewComentarioDTO {
    private String texto;
    private Long idUsuario;
    private Long idSolicitacao;

    //Getters e Setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Long getIdSolicitacao() {
        return idSolicitacao;
    }
    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    
}
