package com.kommunityon.website;

import java.util.List;
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

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {
    @Autowired
    SolicitacaoService solicitacaoService;

    @GetMapping("/solicitacoes/{filtro}")
    public ResponseEntity<List<SolicitacaoDTO>> solicitacoes(@PathVariable String filtro){
        Optional<List<SolicitacaoDTO>> solicitacoesCarregadas = solicitacaoService.solicitacoes(filtro);
        if(solicitacoesCarregadas.isPresent()){
            return ResponseEntity.ok(solicitacoesCarregadas.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/solicitacoes/usuario/{id}")
    public ResponseEntity<List<Solicitacao>> solicitacoesUsuario(@PathVariable Long id){
        Optional<List<Solicitacao>> solicitacoesCarregadas = solicitacaoService.solicitacoesUsuario(id);
        if(solicitacoesCarregadas.isPresent()){
            return ResponseEntity.ok(solicitacoesCarregadas.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Solicitacao> registrar(@RequestBody NewSolicitacaoDTO newSolicitacaoDTO){
        Solicitacao solicitacaoCadastrada = solicitacaoService.registrar(newSolicitacaoDTO);
        if(solicitacaoCadastrada != null){
            return ResponseEntity.ok(solicitacaoCadastrada);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }

    
}
