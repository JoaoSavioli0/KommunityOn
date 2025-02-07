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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SolicitacaoService solicitacaoService;

    @PostMapping("/login")
    public ResponseEntity<Optional<Usuario>> login(@RequestBody LoginDTO loginDto){
        Optional<Usuario> usuarioLogado = usuarioService.login(loginDto);
        if(usuarioLogado.isPresent()){
            return ResponseEntity.ok(usuarioLogado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<List<Usuario>> usuarios(){
        List<Usuario> usuarios = usuarioService.usuarios();
        if(!usuarios.isEmpty()){
            return ResponseEntity.ok(usuarios);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.cadastro(usuario));
    }

    @GetMapping("/curtir/{idUsuario}/{idSolicitacao}")
    public ResponseEntity<String> curte(@PathVariable Long idUsuario, @PathVariable Long idSolicitacao){
        String msg = solicitacaoService.curtir(idUsuario, idSolicitacao);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/solicitacaoaberta/{idUsuario}")
    public ResponseEntity<Boolean> verificaSolicitacaoAberta(@PathVariable Long idUsuario){
        if(solicitacaoService.solicitacaoAberta(idUsuario).isPresent()){
            return ResponseEntity.ok(true); //Tem solicitação aberta 
        }else{
            return ResponseEntity.ok(false); //Não tem solicitação aberta 
        }
    }

    @GetMapping("/interacoes/{idUsuario}")
    public ResponseEntity<List<Long>> interacoesUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.ok(usuarioService.solicitacoes(idUsuario));
    }

}
