package com.kommunityon.website;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SolicitacaoTagRepository extends JpaRepository<SolicitacaoTag, SolicitacaoTagPK> {
    List<SolicitacaoTag> findBySolicitacaoId(Long solicitacaoId);
}
