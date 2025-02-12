package com.kommunityon.website;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioLikeSolicitacaoRepository usuarioLikeSolicitacaoRepository;

    public Optional<Usuario> login(LoginDTO loginDto){
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Optional<Usuario> usuarioLogado;

        if(Pattern.matches(EMAIL_REGEX, loginDto.getCpfOuEmail())){
            usuarioLogado = usuarioRepository.findByEmail(loginDto.getCpfOuEmail());
        }else{
            usuarioLogado = usuarioRepository.findByCpf(loginDto.getCpfOuEmail());
        }
        
        if(usuarioLogado.isPresent()){
            if(loginDto.getSenha().equals(usuarioLogado.get().getSenha())){
                return usuarioLogado;
            }
        }
        return Optional.empty();
    }

    public Usuario cadastro(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> usuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> usuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public List<Long> solicitacoes(Long idUsuario){
        return usuarioLikeSolicitacaoRepository.findSolicitacaoIdsByUsuario(idUsuario);
    }
}
