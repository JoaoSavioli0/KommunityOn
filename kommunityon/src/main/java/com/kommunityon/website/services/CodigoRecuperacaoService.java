package com.kommunityon.website.services;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kommunityon.website.repositories.CodigoRecuperacaoRepository;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.entities.CodigoRecuperacao;
import com.kommunityon.website.dtos.CodigoRecuperacaoDTO;

@Service
public class CodigoRecuperacaoService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CodigoRecuperacaoRepository codigoRecuperacaoRepository;

    public HashMap<String, String> geraCodigo(String cpfOuEmail){
        Optional<Usuario> usuarioOptional = usuarioService.usuarioPorCpfOuEmail(cpfOuEmail);
        String codigo;
        CodigoRecuperacao codigoRecuperacao = new CodigoRecuperacao();
        HashMap<String, String> info = new HashMap<>();

        System.out.println("cpfOuEmail: " + cpfOuEmail);
        System.out.println("UsuarioOptional: " + usuarioOptional);

        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            Random random = new Random();
            StringBuilder sb;

            do{

            sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(random.nextInt(10)); // Gera números de 0 a 9
            }
            codigo = sb.toString();
            
            }while(codigoRecuperacaoRepository.findByCodigo(codigo).isPresent());

            codigoRecuperacao.setCodigo(codigo);
            codigoRecuperacao.setUsuario(usuario);

            codigoRecuperacaoRepository.save(codigoRecuperacao);

            info.put("codigo", codigo);
            info.put("telefone", usuario.getTelefone());
            info.put("email", usuario.getEmail());

            return info;

        }else{
            return null;
        }
    }

    public Long certificaCodigo(CodigoRecuperacaoDTO codigoRecuperacaoDTO){
        System.out.println("Código: " + codigoRecuperacaoDTO.getCodigo());
        System.out.println("email: " + codigoRecuperacaoDTO.getcpfOuEmail());
        Optional<CodigoRecuperacao> codigoOptional = codigoRecuperacaoRepository.findByCodigo(codigoRecuperacaoDTO.getCodigo());
        Optional<Usuario> usuarioOptional = usuarioService.usuarioPorCpfOuEmail(codigoRecuperacaoDTO.getcpfOuEmail());
        
        if(usuarioOptional.isPresent() && codigoOptional.isPresent()){
            Long idUsuario = usuarioOptional.get().getId();

            CodigoRecuperacao codigoRecuperacao = codigoOptional.get();
            if(Objects.equals(codigoRecuperacao.getUsuario().getId(), idUsuario)){
                return idUsuario;
            }
        }
        return Long.valueOf(0);
    }
}
