package com.kommunityon.website.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kommunityon.website.entities.Ecopoint;

@Repository
public interface EcopointRepository extends JpaRepository<Ecopoint, Long> {
    
}
