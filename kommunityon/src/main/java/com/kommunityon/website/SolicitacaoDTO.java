package com.kommunityon.website;

import java.time.LocalDateTime;

public class SolicitacaoDTO {

    public SolicitacaoDTO(Long id, String titulo, Long idUsuario, String bairro, String descricao, int numLikes,
            Long numComentarios, LocalDateTime dataAbertura, LocalDateTime dataConclusao, int anonimo) {
        this.id = id;
        this.titulo = titulo;
        this.idUsuario = idUsuario;
        this.bairro = bairro;
        this.descricao = descricao;
        this.numLikes = numLikes;
        this.numComentarios = numComentarios;
        this.dataAbertura = dataAbertura;
        this.dataConclusao = dataConclusao;
        this.anonimo = anonimo;
    }

    private Long id;

    private String titulo;
    private Long idUsuario;
    private String bairro;
    private String descricao;
    private int numLikes;
    private Long numComentarios;
    private LocalDateTime dataConclusao;
    private int anonimo;
    private LocalDateTime dataAbertura;

    // Getters & Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getUsuarioId() {
        return idUsuario;
    }

    public void setUsuarioId(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int num_likes) {
        this.numLikes = num_likes;
    }

    public Long getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(Long num_comentarios) {
        this.numComentarios = num_comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public int getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(int anonimo) {
        this.anonimo = anonimo;
    }

}
