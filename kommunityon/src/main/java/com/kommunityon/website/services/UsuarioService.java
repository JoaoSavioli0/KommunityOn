package com.kommunityon.website.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kommunityon.website.dtos.LoginDTO;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.repositories.UsuarioLikeSolicitacaoRepository;
import com.kommunityon.website.repositories.UsuarioRepository;

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

    public Optional<Usuario> usuarioPorCpfOuEmail(String cpfOuEmail){
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Optional<Usuario> usuario;
        System.out.println("cpf ou email: " + cpfOuEmail);

        if(Pattern.matches(EMAIL_REGEX, cpfOuEmail)){
            usuario = usuarioRepository.findByEmail(cpfOuEmail);
        }else{
            cpfOuEmail = cpfOuEmail.replaceAll("\\D", "");
            System.out.println("Procurou por cpf: " + cpfOuEmail);
            usuario = usuarioRepository.findByCpf(cpfOuEmail);
        }

        if(usuario.isPresent()){
            System.out.println("Achou");
            return usuario;
        }
        return Optional.empty();
    }

    public Optional<Usuario> loginToken(String emailCpf){
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if(Pattern.matches(EMAIL_REGEX, emailCpf)){
            return usuarioRepository.findByEmail(emailCpf);
        }else{
            return usuarioRepository.findByCpf(emailCpf);
        }
    }

    public Usuario cadastro(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario atualiza(Usuario novosDados){
        Optional<Usuario> dadosAtuais = usuarioRepository.findById(novosDados.getId());

        if(dadosAtuais.isPresent()){
            Usuario dadosAtualizados = dadosAtuais.get();

            dadosAtualizados.setTelefone(novosDados.getTelefone());
            dadosAtualizados.setEmail(novosDados.getEmail());
            dadosAtualizados.setNome(novosDados.getNome());
            
            return usuarioRepository.save(dadosAtualizados);
        }
        return null;
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

    public String uploadFoto(Long id, MultipartFile foto){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if(usuarioEncontrado.isPresent()){
            Usuario usuario = usuarioEncontrado.get();
            try{
                usuario.setFotoPerfil(foto.getBytes());
                usuarioRepository.save(usuario);
                return "Imagem salva com sucesso!";
            } catch (IOException e) {
                return "Erro ao processar a imagem: " + e;
            }
        } else {
            return "Erro ao identificar usuário.";
        }
    }

    public String getFoto(Long id){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if(usuarioEncontrado.isPresent()){
            Usuario usuario = usuarioEncontrado.get();
            if(usuario.getFotoPerfil()!=null)
            return Base64.getEncoder().encodeToString(usuario.getFotoPerfil());
        }
        return "sem foto";
        
    }
}
