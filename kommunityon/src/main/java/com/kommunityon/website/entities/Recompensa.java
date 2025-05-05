package com.kommunityon.website.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recompensa {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nome;
    private int preco;
    private int estoque;
    private String descricao;


    //Descricao
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
