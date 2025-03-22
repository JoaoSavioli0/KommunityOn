package com.kommunityon.website.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kommunityon.website.entities.CodigoRecuperacao;

public interface CodigoRecuperacaoRepository extends JpaRepository<CodigoRecuperacao, Long>{
    public Optional<CodigoRecuperacao> findByCodigo(String codigo);
}
