package com.kommunityon.website;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Endereco cadastro(Endereco endereco){
        Optional<Usuario> usuario = usuarioRepository.findById(endereco.getUsuario().getId());
        if(usuario.isPresent()){
            endereco.setUsuario(usuario.get());
            return enderecoRepository.save(endereco);
        }else{
            return null;
        }      
    }

    public Optional<Endereco> enderecoUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return enderecoRepository.findByUsuario(usuario.get());
        }else{
            return Optional.empty();
        }

    }
}
