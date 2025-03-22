package com.kommunityon.website.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String usuarioEmail);
    Optional<Usuario> findByCpf(String usuarioCpf);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.dataInteracao1 = :data WHERE u.id = :id")
    int atualizarDataInteracao1(@Param("id") Long id, @Param("data") LocalDateTime data);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.dataInteracao2 = :data WHERE u.id = :id")
    int atualizarDataInteracao2(@Param("id") Long id, @Param("data") LocalDateTime data); 

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.senha = :senha WHERE u.id = :id")
    int atualizaSenha(@Param("senha") String senha, @Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.fotoPerfil = NULL WHERE u.id = :id")
    int removeImagem(@Param("id") Long id);
}