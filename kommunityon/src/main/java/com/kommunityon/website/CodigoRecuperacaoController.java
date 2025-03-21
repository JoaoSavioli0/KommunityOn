package com.kommunityon.website;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recuperacao")
public class CodigoRecuperacaoController {

    @Autowired
    CodigoRecuperacaoService codigoRecuperacaoService;

    @PostMapping("/envia_codigo")
    ResponseEntity<HashMap<String, String>> enviaCodigo(@RequestBody Map<String, String> request) {
        String cpfOuEmail = request.get("cpfOuEmail");
        System.out.println("cpfOuEmail antes do geraCodigo: " + cpfOuEmail);
        HashMap<String, String> info = codigoRecuperacaoService.geraCodigo(cpfOuEmail);
        System.out.println("Info: " + info);
        if (info != null) {
            return ResponseEntity.ok(info);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/verifica_codigo")
    ResponseEntity<Long> verificaCodigo(@RequestBody CodigoRecuperacaoDTO codigoRecuperacaoDTO){
        Long valido = codigoRecuperacaoService.certificaCodigo(codigoRecuperacaoDTO);
        return ResponseEntity.ok(valido);
    }
}
