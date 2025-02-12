package com.kommunityon.website;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLikeSolicitacaoRepository extends JpaRepository<UsuarioLikeSolicitacao, Long> {
    @Query("SELECT uls.solicitacao.id FROM UsuarioLikeSolicitacao uls WHERE uls.usuario.id = :idUsuario")
    List<Long> findSolicitacaoIdsByUsuario(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Query("DELETE FROM UsuarioLikeSolicitacao uls where uls.solicitacao.id = :idSolicitacao")
    void deleteBySolicitacaoId(@Param("idSolicitacao") Long id);
}
