package com.kommunityon.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kommunityon.website.entities.Conquista;
import com.kommunityon.website.services.ConquistaService;

@RestController
@RequestMapping("/conquista")
public class ConquistaController {
    
    @Autowired
    ConquistaService conquistaService;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> conquistasUsuarioController(@PathVariable Long id) {
       List<Conquista> conquistas = conquistaService.conquistasUsuarioService(id);

       if(!conquistas.isEmpty()){
        return ResponseEntity.ok(conquistas);
       }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário sem conquistas");
       }
    }
    
}
