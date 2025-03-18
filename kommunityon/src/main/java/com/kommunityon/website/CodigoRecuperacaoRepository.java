package com.kommunityon.website;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoRecuperacaoRepository extends JpaRepository<CodigoRecuperacao, Long>{
    public Optional<CodigoRecuperacao> findByCodigo(String codigo);
}
