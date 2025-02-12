package com.kommunityon.website;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
    @Query("SELECT new com.kommunityon.website.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes, s.numComentarios) FROM Solicitacao s INNER JOIN s.usuario u WHERE s.dataConclusao IS NULL ORDER BY s.numLikes desc")
    Optional<List<SolicitacaoDTO>> findAllPorLike(); 

    @Query("SELECT new com.kommunityon.website.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes, s.numComentarios) FROM Solicitacao s INNER JOIN s.usuario u WHERE s.dataConclusao IS NULL ORDER BY s.dataAbertura desc")
    Optional<List<SolicitacaoDTO>> findAllPorData();
    
    @Transactional
    @Modifying
    @Query("UPDATE Solicitacao s SET s.numLikes = s.numLikes + 1 WHERE s.id = :id")
    int incrementaLike(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Solicitacao s SET s.numComentarios = :numComentarios WHERE s.id = :id")
    int incrementaComentario(@Param("numComentarios") int numComentarios, @Param("id") Long id);

    @Query("SELECT s.id FROM Solicitacao s WHERE s.dataConclusao IS NULL AND s.usuario.id = :id")
    Optional<Long> verificaSolicitacaoAberta(@Param("id") Long id);

    Optional<List<Solicitacao>> findByUsuario(Usuario usuario);
}
