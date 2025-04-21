package com.kommunityon.website.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.Recompensa;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
    public List<Recompensa> findByIdIn(List<Long> ids);
}
