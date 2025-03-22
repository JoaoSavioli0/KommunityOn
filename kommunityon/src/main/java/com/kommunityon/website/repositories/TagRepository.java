package com.kommunityon.website.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kommunityon.website.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
