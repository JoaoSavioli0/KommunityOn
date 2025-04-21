package com.kommunityon.website.dtos;

public class RecompensaComIdUnicoDTO {
    private Long idUnico;
    private Long idRecompensa;
    private String nome;
    private String descricao;
    private int preco;
    private int estoque;
    private boolean ativado;

    public RecompensaComIdUnicoDTO(Long idUnico, Long idRecompensa, String nome, String descricao, int preco, int estoque, boolean ativado){
        this.idUnico = idUnico;
        this.idRecompensa = idRecompensa;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.ativado = ativado;
    }


    //Getters e Setters
    public Long getIdUnico() {
        return idUnico;
    }
    public void setIdUnico(Long idUnico) {
        this.idUnico = idUnico;
    }
    public Long getIdRecompensa() {
        return idRecompensa;
    }
    public void setIdRecompensa(Long idRecompensa) {
        this.idRecompensa = idRecompensa;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isAtivado() {
        return ativado;
    }
    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }
    
}
