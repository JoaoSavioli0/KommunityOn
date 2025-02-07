package com.kommunityon.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @PostMapping("/cadastro")
    public ResponseEntity<Endereco> cadastro(@RequestBody Endereco endereco){
        return ResponseEntity.ok(enderecoService.cadastro(endereco));
    }
}
