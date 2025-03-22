package com.kommunityon.website.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kommunityon.website.entities.Comentario;
import com.kommunityon.website.entities.Solicitacao;
import com.kommunityon.website.entities.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public Optional<List<Comentario>> findByUsuario(Usuario usuario);
    public Optional<List<Comentario>> findBySolicitacao(Solicitacao solicitacao);

    @Modifying
    @Query("DELETE FROM Comentario c where c.solicitacao.id = :idSolicitacao")
    void deleteBySolicitacaoId(@Param("idSolicitacao") Long id);
}
