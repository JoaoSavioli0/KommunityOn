package com.kommunityon.website;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {
    @Autowired
    SolicitacaoService solicitacaoService;

    @Autowired
    ComentarioRepository comentarioRepository;

    @PostMapping("/solicitacoes")
    public ResponseEntity<List<SolicitacaoDTO>> solicitacoes(@RequestBody List<Integer> tagId){
        List<SolicitacaoDTO> solicitacoesCarregadas = solicitacaoService.solicitacoes(tagId);
        return ResponseEntity.ok(solicitacoesCarregadas);
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<Integer> comentariosSolicitacao(@RequestParam Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoService.solicitacaoPorId(id);
        if(solicitacaoOptional.isPresent()){
            Optional<List<Comentario>> comentariosOptional = comentarioRepository.findBySolicitacao(solicitacaoOptional.get());
            return ResponseEntity.ok(comentariosOptional.get().size());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    @PostMapping("/solicitacoes/usuario/{id}")
    public ResponseEntity<List<Solicitacao>> solicitacoesUsuario(@PathVariable Long id, @RequestBody List<Integer> tagId){
        List<Solicitacao> solicitacoesCarregadas = solicitacaoService.solicitacoesUsuario(id, tagId);
        return ResponseEntity.ok(solicitacoesCarregadas);
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

    @GetMapping("/tags/{id}")
    public ResponseEntity<List<Tag>> tagsSolicitacao(@PathVariable Long id){
        return ResponseEntity.ok(solicitacaoService.tags(id)); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> solicitacao(@PathVariable Long id){
        Optional<Solicitacao> solicitacaoEncontrada = solicitacaoService.solicitacaoPorId(id);
        if(solicitacaoEncontrada.isPresent()){
            return ResponseEntity.ok(solicitacaoEncontrada.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/exclui/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        solicitacaoService.excluiSolicitacao(id);
    }

    @PatchMapping("/conclui/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void concluir (@PathVariable Long id){
        solicitacaoService.concluiSolictacao(id);
    }
}
