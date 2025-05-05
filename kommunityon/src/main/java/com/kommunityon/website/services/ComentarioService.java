package com.kommunityon.website.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kommunityon.website.dtos.NewComentarioDTO;
import com.kommunityon.website.entities.Comentario;
import com.kommunityon.website.entities.Solicitacao;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.repositories.ComentarioRepository;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SolicitacaoService solicitacaoService;

    @Autowired
    ConquistaService conquistaService;

    public Optional<Comentario> porId(Long id){
        return comentarioRepository.findById(id);
    }

    public Optional<List<Comentario>> porUsuario(Usuario usuario){
        return comentarioRepository.findByUsuario(usuario);
    }

    public Optional<List<Comentario>> porSolicitacao(Solicitacao solicitacao){
        return comentarioRepository.findBySolicitacao(solicitacao);
    }

    public Comentario novoComentario(NewComentarioDTO comentarioDTO){
        Optional<Usuario> usuarioComentario = usuarioService.usuarioPorId(comentarioDTO.getIdUsuario());
        Optional<Solicitacao> solicitacaoComentario = solicitacaoService.solicitacaoPorId(comentarioDTO.getIdSolicitacao());

        if(usuarioComentario.isPresent() && solicitacaoComentario.isPresent()){
            Comentario novoComentario = new Comentario();
            novoComentario.setSolicitacao(solicitacaoComentario.get());
            novoComentario.setUsuario(usuarioComentario.get());
            novoComentario.setTexto(comentarioDTO.getTexto());

            int numComentarios = comentarioRepository.findBySolicitacao(solicitacaoComentario.get()).get().size() + 1;

            solicitacaoService.atualizarNumComentarios(numComentarios, comentarioDTO.getIdSolicitacao());

            int numComentariosUsuario = porUsuario(usuarioComentario.get()).isPresent() ? porUsuario(usuarioComentario.get()).get().size() : 0;

            conquistaService.verificaConquista_5Comentarios(usuarioComentario.get(), numComentariosUsuario);

            return comentarioRepository.save(novoComentario);
        }else{
            return null;
        }
    }
}
