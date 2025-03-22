package com.kommunityon.website.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kommunityon.website.entities.Endereco;
import com.kommunityon.website.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @PostMapping("/cadastro")
    public ResponseEntity<Endereco> cadastro(@RequestBody Endereco endereco){
        return ResponseEntity.ok(enderecoService.cadastro(endereco));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> enderecoUsuario(@PathVariable Long id){
        Optional<Endereco> enderecoEncontrado = enderecoService.enderecoUsuario(id);
        if(enderecoEncontrado.isPresent()){
            return ResponseEntity.ok(enderecoEncontrado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
