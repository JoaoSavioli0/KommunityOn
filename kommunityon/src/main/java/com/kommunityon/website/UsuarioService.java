package com.kommunityon.website;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
            return Base64.getEncoder().encodeToString(usuario.getFotoPerfil());
        }else{
            return "";
        }
    }
}
