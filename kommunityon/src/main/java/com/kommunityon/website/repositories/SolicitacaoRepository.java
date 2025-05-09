package com.kommunityon.website.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.dtos.SolicitacaoDTO;
import com.kommunityon.website.entities.Solicitacao;
import com.kommunityon.website.entities.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
   @Query("SELECT new com.kommunityon.website.dtos.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes, " +
       "(SELECT COUNT(c) FROM Comentario c WHERE c.solicitacao.id = s.id), " +
       "s.dataAbertura, s.dataConclusao, s.anonimo) " +
       "FROM Solicitacao s " +
       "LEFT JOIN s.usuario u " +
       "LEFT JOIN SolicitacaoTag st ON s.id = st.solicitacaoId " +
       "WHERE st.id IS NULL")
Optional<List<SolicitacaoDTO>> findAllSemTag();

    @Query("SELECT new com.kommunityon.website.dtos.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes, " + 
      "(SELECT COUNT(c) FROM Comentario c WHERE c.solicitacao.id = s.id), s.dataAbertura, s.dataConclusao, s.anonimo) " +
      "FROM Solicitacao s " +
      "INNER JOIN s.usuario u")
    Optional<List<SolicitacaoDTO>> findAllSemFiltro(); 

    @Query("SELECT DISTINCT new com.kommunityon.website.dtos.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes," +
      "(SELECT COUNT(c) FROM Comentario c WHERE c.solicitacao.id = s.id), s.dataAbertura, s.dataConclusao, s.anonimo) " +
      "FROM Solicitacao s " +
      "INNER JOIN s.usuario u " +
      "INNER JOIN SolicitacaoTag st ON st.solicitacaoId = s.id " +
      "WHERE st.tag.id IN (:tagId)")
    Optional<List<SolicitacaoDTO>> findAllPorTag(@Param("tagId") List<Integer> tagId);

    @Query("SELECT new com.kommunityon.website.dtos.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes, " + 
      "(SELECT COUNT(c) FROM Comentario c WHERE c.solicitacao.id = s.id), s.dataAbertura, s.dataConclusao, s.anonimo) " +
      "FROM Solicitacao s " +
      "LEFT JOIN s.usuario u " +
      "WHERE u.id = :id")
    Optional<List<SolicitacaoDTO>> findAllPorUsuario(@Param("id") Long id);

    @Query("SELECT new com.kommunityon.website.dtos.SolicitacaoDTO(s.id, s.titulo, u.id, s.bairro, s.descricao, s.numLikes, " + 
      "(SELECT COUNT(c) FROM Comentario c WHERE c.solicitacao.id = s.id), s.dataAbertura, s.dataConclusao, s.anonimo) " +
      "FROM Solicitacao s " +
      "LEFT JOIN s.usuario u " +
       "INNER JOIN SolicitacaoTag st ON st.solicitacaoId = s.id " +
       "WHERE st.tag.id IN (:tagId) " +
       "AND u.id = :id")
    Optional<List<SolicitacaoDTO>> findAllPorUsuarioComTag(@Param("id") Long id, @Param("tagId") List<Integer> tagId);
    
    @Transactional
    @Modifying
    @Query("UPDATE Solicitacao s SET s.numLikes = s.numLikes + 1 WHERE s.id = :id")
    int incrementaLike(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Solicitacao s SET s.numComentarios = :numComentarios WHERE s.id = :id")
    int incrementaComentario(@Param("numComentarios") int numComentarios, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Solicitacao s SET s.dataConclusao = :data WHERE s.id = :id")
    int concluiSolicitacao(@Param("data") LocalDateTime data, @Param("id") Long id);

    @Query("SELECT s FROM Solicitacao s WHERE s.dataConclusao IS NULL AND s.usuario.id = :id")
    Optional<ArrayList<Solicitacao>> verificaSolicitacaoAberta(@Param("id") Long id);

    Optional<List<Solicitacao>> findByUsuario(Usuario usuario);
}
