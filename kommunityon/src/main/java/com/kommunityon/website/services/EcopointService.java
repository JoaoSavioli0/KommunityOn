package com.kommunityon.website.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kommunityon.website.entities.Ecopoint;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.repositories.EcopointRepository;
import com.kommunityon.website.repositories.UsuarioRepository;

@Service
public class EcopointService {
    @Autowired
    EcopointRepository ecopointRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Ecopoint> porUsuario(Long id){
        return ecopointRepository.findById(id);
    }

    public Ecopoint primeiroPonto(Usuario usuario){
        Ecopoint novoEcopoint = new Ecopoint();
        novoEcopoint.setUsuario(usuario);
        novoEcopoint.setPontos(0);
        return ecopointRepository.save(novoEcopoint);
    }

    public void adicionaPontos(int quantidade, Usuario usuario){
        Optional<Ecopoint> ecopointOptional = ecopointRepository.findById(usuario.getId());
        Ecopoint ecopoint;

        if(ecopointOptional.isEmpty()){
            ecopoint = primeiroPonto(usuario);
        }else{
            ecopoint = ecopointOptional.get();
        }

        if(ecopoint!=null){
            ecopoint.setPontos(ecopoint.getPontos() + quantidade);
            ecopointRepository.save(ecopoint);
        }
    }
}
