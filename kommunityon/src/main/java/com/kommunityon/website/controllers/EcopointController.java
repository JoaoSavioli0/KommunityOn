package com.kommunityon.website.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kommunityon.website.entities.Ecopoint;
import com.kommunityon.website.repositories.EcopointRepository;

@RestController
@RequestMapping("/ecopoint")
public class EcopointController {
    @Autowired
    EcopointRepository ecopointRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPontos(@PathVariable Long id) {
        Optional<Ecopoint> ecopointOptional = ecopointRepository.findById(id);
        
        if(ecopointOptional.isPresent()){
            return ResponseEntity.ok(ecopointOptional.get().getPontos());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não existe ou não registrado na tabela");
        }
    }
    
}
