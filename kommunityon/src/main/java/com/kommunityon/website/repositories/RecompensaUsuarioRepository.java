package com.kommunityon.website.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.Recompensa;
import com.kommunityon.website.entities.RecompensaUsuario;
import com.kommunityon.website.entities.Usuario;

@Repository
public interface RecompensaUsuarioRepository extends JpaRepository<RecompensaUsuario, Long> {
    public Optional<RecompensaUsuario> findByUsuario(Usuario usuario);
    public Optional<RecompensaUsuario> findByRecompensa(Recompensa recompensa);
    public List<RecompensaUsuario> findAllByUsuario(Usuario usuario);
}
