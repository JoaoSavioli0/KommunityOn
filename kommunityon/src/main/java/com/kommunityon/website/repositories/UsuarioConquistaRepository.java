package com.kommunityon.website.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.UsuarioConquista;
import com.kommunityon.website.entities.UsuarioConquistaPK;

@Repository
public interface UsuarioConquistaRepository extends JpaRepository<UsuarioConquista, UsuarioConquistaPK> {
    public List<UsuarioConquista> findByUsuarioId(Long id); 
    public List<UsuarioConquista> findByConquistaId(Long id);
    
    @Query("SELECT uc.conquistaId FROM UsuarioConquista uc WHERE uc.usuarioId = :usuarioId")
    List<Long> findConquistaIdByUsuarioId(@Param("usuarioId") Long usuarioId);
}
