package com.kommunityon.website;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;

    @Column(name = "tipo", insertable = false)
    private String tipo = "USU";

    @Column(name = "data_interacao_1", insertable = false)
    private LocalDateTime dataInteracao1;

    @Column(name = "data_interacao_2", insertable = false)
    private LocalDateTime dataInteracao2;

    @Lob
    @Column(name = "foto_perfil", columnDefinition = "MEDIUMBLOB")
    private byte[] fotoPerfil;
    

    //Getters & Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataInteracao2() {
        return dataInteracao2;
    }

    public void setDataInteracao2(LocalDateTime dataInteracao2) {
        this.dataInteracao2 = dataInteracao2;
    }

    public LocalDateTime getDataInteracao1() {
        return dataInteracao1;
    }

    public void setDataInteracao1(LocalDateTime dataInteracao1) {
        this.dataInteracao1 = dataInteracao1;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    
}
