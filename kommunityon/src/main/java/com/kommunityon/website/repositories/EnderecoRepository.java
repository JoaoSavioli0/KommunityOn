package com.kommunityon.website.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kommunityon.website.entities.Endereco;
import com.kommunityon.website.entities.Usuario;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByUfAndCidadeAndBairro(String uf, String cidade, String bairro);

    Optional<Endereco> findByUsuario(Usuario usuario);
}

