package com.kommunityon.website;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public Optional<List<Comentario>> findByUsuario(Usuario usuario);
    public Optional<List<Comentario>> findBySolicitacao(Solicitacao solicitacao);
}
