package com.kommunityon.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kommunityon.website.dtos.RecompensaComIdUnicoDTO;
import com.kommunityon.website.dtos.ResgateRecompensaDTO;
import com.kommunityon.website.entities.Recompensa;
import com.kommunityon.website.repositories.RecompensaRepository;
import com.kommunityon.website.services.RecompensaService;




@RestController
@RequestMapping("/recompensa")
public class RecompensaController {
    @Autowired
    RecompensaRepository recompensaRepository;

    @Autowired
    RecompensaService recompensaService;

    @GetMapping("/recompensas")
    public ResponseEntity<List<Recompensa>> getRecompensas() {
        return ResponseEntity.ok(recompensaRepository.findAll());
    }
    
    @PostMapping("/resgate")
    public ResponseEntity<String> resgateRecompensa(@RequestBody ResgateRecompensaDTO resgateRecompensaDTO) {
        return ResponseEntity.ok(recompensaService.resgataRecompensaService(resgateRecompensaDTO));
    }

    @GetMapping("/recompensas/{id}")
    public ResponseEntity<List<RecompensaComIdUnicoDTO>> recompensasUsuarioController(@PathVariable Long id) {
        return ResponseEntity.ok(recompensaService.recompensasUsuarioService(id));
    }
    
    @PostMapping("/ativar/{id}")
    public ResponseEntity<String> ativaRecompensa(@PathVariable Long id) {
        return ResponseEntity.ok(recompensaService.ativaRecompensaService(id));
    }
    
    
}
