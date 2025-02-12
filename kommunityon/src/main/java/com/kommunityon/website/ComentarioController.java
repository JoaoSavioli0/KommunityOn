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
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    ComentarioService comentarioService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SolicitacaoService solicitacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> comentarioPorId(@PathVariable Long id){
        Optional<Comentario> comentarioEncontrado = comentarioService.porId(id);
        if(comentarioEncontrado.isPresent()){
            return ResponseEntity.ok(comentarioEncontrado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Comentario>> comentarioPorUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioEncontrado = usuarioService.usuarioPorId(id);
        if(usuarioEncontrado.isPresent()){
            Optional<List<Comentario>> comentariosEncontrados = comentarioService.porUsuario(usuarioEncontrado.get());
            if(comentariosEncontrados.isPresent()){
                return ResponseEntity.ok(comentariosEncontrados.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }   
    }

    @GetMapping("/solicitacao/{id}")
    public ResponseEntity<List<Comentario>> comentarioPorSolicitacao(@PathVariable Long id){
        Optional<Solicitacao> solicitacaoEncontrada = solicitacaoService.solicitacaoPorId(id);
        if(solicitacaoEncontrada.isPresent()){
            Optional<List<Comentario>> comentariosEncontrados = comentarioService.porSolicitacao(solicitacaoEncontrada.get());
            if(comentariosEncontrados.isPresent()){
                return ResponseEntity.ok(comentariosEncontrados.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }   
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Comentario> cadastroComentario(@RequestBody NewComentarioDTO comentarioDTO){
        return ResponseEntity.ok(comentarioService.novoComentario(comentarioDTO));
    }
}
