package com.kommunityon.website.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kommunityon.website.dtos.SolicitacaoDTO;
import com.kommunityon.website.entities.Conquista;
import com.kommunityon.website.entities.Solicitacao;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.entities.UsuarioConquista;
import com.kommunityon.website.repositories.ConquistaRepository;
import com.kommunityon.website.repositories.SolicitacaoRepository;
import com.kommunityon.website.repositories.UsuarioConquistaRepository;
import com.kommunityon.website.repositories.UsuarioRepository;

@Service
public class ConquistaService {
    
    @Autowired
    ConquistaRepository conquistaRepository;

    @Autowired
    UsuarioConquistaRepository usuarioConquistaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    public List<Conquista> conquistasUsuarioService(Long id){
        List<UsuarioConquista> conquistasUsuario = usuarioConquistaRepository.findByUsuarioId(id);
        List<Long> idConquistas = new ArrayList<>();

        for(UsuarioConquista conquista : conquistasUsuario){
            idConquistas.add(conquista.getConquistaId());
        }

        return conquistaRepository.findAllById(idConquistas);
    }

    public void verificaConquista_10Likes(Solicitacao solicitacao){
        Usuario criador = usuarioRepository.findById(solicitacao.getUsuario().getId()).get();
        List<Long> conquistasUsuario = usuarioConquistaRepository.findConquistaIdByUsuarioId(criador.getId());
        Conquista conquista = conquistaRepository.findById(Long.valueOf(2)).get();

        if(!conquistasUsuario.contains(Long.valueOf(2))){
            UsuarioConquista novaConquista = new UsuarioConquista(criador, conquista);
            usuarioConquistaRepository.save(novaConquista);
        }
    }

    public void verificaConquista_5Comentarios(Usuario usuario, int numComentarios){
        List<Long> conquistasUsuario = usuarioConquistaRepository.findConquistaIdByUsuarioId(usuario.getId());
        Conquista conquista = conquistaRepository.findById(Long.valueOf(3)).get();

        if(!conquistasUsuario.contains(Long.valueOf(3))){
            UsuarioConquista novaConquista = new UsuarioConquista(usuario, conquista);
            usuarioConquistaRepository.save(novaConquista);
        }
    }

    public void verificaConquista_1Solicitacao(Usuario usuario){
        Optional<List<SolicitacaoDTO>> solicitacoes = solicitacaoRepository.findAllPorUsuario(usuario.getId());

        if(solicitacoes.isPresent()){
            List<Long> conquistasUsuario = usuarioConquistaRepository.findConquistaIdByUsuarioId(usuario.getId());
            
            if(!conquistasUsuario.contains(Long.valueOf(1))){
                Conquista conquista = conquistaRepository.findById(Long.valueOf(1)).get();
                UsuarioConquista novaConquista = new UsuarioConquista(usuario, conquista);
                usuarioConquistaRepository.save(novaConquista);
            }
        }
    }
}
