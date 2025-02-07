package com.kommunityon.website;

public class SolicitacaoDTO {

    public SolicitacaoDTO(Long id, String titulo, Long idUsuario, String bairro, String descricao, int num_likes, int num_comentarios){
        this.id = id;
        this.titulo = titulo;
        this.idUsuario = idUsuario;
        this.bairro = bairro;
        this.descricao = descricao;
        this.num_likes = num_likes;
        this.num_comentarios = num_comentarios;
    }

    private Long id;

    private String titulo;
    private Long idUsuario;
    private String bairro;
    private String descricao;
    private int num_likes;
    private int num_comentarios;

    //Getters & Setters
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
    public int getNum_likes() {
        return num_likes;
    }
    public void setNum_likes(int num_likes) {
        this.num_likes = num_likes;
    }
    public int getNum_comentarios() {
        return num_comentarios;
    }
    public void setNum_comentarios(int num_comentarios) {
        this.num_comentarios = num_comentarios;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}
