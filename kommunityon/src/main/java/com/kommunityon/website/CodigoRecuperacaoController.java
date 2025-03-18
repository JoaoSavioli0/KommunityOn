package com.kommunityon.website;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recuperacao")
public class CodigoRecuperacaoController {

    @Autowired
    CodigoRecuperacaoService codigoRecuperacaoService;
    
    @GetMapping("/envia_codigo")
    ResponseEntity<?> enviaCodigo(@RequestParam("cpfOuEmail") String cpfOuEmail){
        CodigoRecuperacao codigo = codigoRecuperacaoService.geraCodigo(cpfOuEmail);
    }
}
