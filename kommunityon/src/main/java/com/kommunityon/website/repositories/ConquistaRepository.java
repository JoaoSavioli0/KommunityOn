package com.kommunityon.website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.Conquista;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    
}
