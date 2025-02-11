package com.kommunityon.website;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByUfAndCidadeAndBairro(String uf, String cidade, String bairro);

    Optional<Endereco> findByUsuario(Usuario usuario);
}

