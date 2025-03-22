package com.kommunityon.website.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.SolicitacaoTag;
import com.kommunityon.website.entities.SolicitacaoTagPK;

@Repository
public interface SolicitacaoTagRepository extends JpaRepository<SolicitacaoTag, SolicitacaoTagPK> {
    List<SolicitacaoTag> findBySolicitacaoId(Long solicitacaoId);
}
