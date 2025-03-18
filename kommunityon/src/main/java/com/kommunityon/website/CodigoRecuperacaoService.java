package com.kommunityon.website;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoRecuperacaoService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CodigoRecuperacaoRepository codigoRecuperacaoRepository;

    public CodigoRecuperacao geraCodigo(String cpfOuEmail){
        Optional<Usuario> usuarioOptional = usuarioService.usuarioPorCpfOuEmail(cpfOuEmail);
        String codigo;
        CodigoRecuperacao codigoRecuperacao = new CodigoRecuperacao();

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

            return codigoRecuperacaoRepository.save(codigoRecuperacao);

        }else{
            return null;
        }
    }
}
