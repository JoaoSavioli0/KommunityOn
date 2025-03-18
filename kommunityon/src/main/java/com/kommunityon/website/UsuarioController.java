package com.kommunityon.website;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SolicitacaoService solicitacaoService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto){
        Optional<Usuario> usuarioLogado = usuarioService.login(loginDto);
        if(usuarioLogado.isPresent()){
            String token = jwtService.generateToken(loginDto.getCpfOuEmail());
            HashMap<String, Object> response = new HashMap();
            response.put("token", token);
            response.put("usuario", usuarioLogado.get());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> validaToken(@RequestHeader("Authorization") String token){
        try {
            String jwt = token.replace("Bearer ", "");
    
            String emailCpf = jwtService.validateTokenAndGetUsername(jwt);
    
            Usuario usuario = usuarioService.loginToken(emailCpf).get();
    
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<List<Usuario>> usuarios(){
        List<Usuario> usuarios = usuarioService.usuarios();
        if(!usuarios.isEmpty()){
            return ResponseEntity.ok(usuarios);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> usuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.usuarioPorId(id);

        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.cadastro(usuario));
    }

    @PostMapping("/atualiza/{id}")
    public ResponseEntity<Usuario> atualiza(@RequestBody Usuario novosDados){
        Usuario usuarioAtualizado = usuarioService.atualiza(novosDados);
        
        if(usuarioAtualizado != null){
            return ResponseEntity.ok(usuarioAtualizado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/curtir/{idUsuario}/{idSolicitacao}")
    public ResponseEntity<String> curte(@PathVariable Long idUsuario, @PathVariable Long idSolicitacao){
        String msg = solicitacaoService.curtir(idUsuario, idSolicitacao);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/solicitacaoaberta/{idUsuario}")
    public ResponseEntity<Boolean> verificaSolicitacaoAberta(@PathVariable Long idUsuario){
        if(solicitacaoService.solicitacaoAberta(idUsuario).isPresent()){
            return ResponseEntity.ok(true); //Tem solicitação aberta 
        }else{
            return ResponseEntity.ok(false); //Não tem solicitação aberta 
        }
    }

    @GetMapping("/interacoes/{idUsuario}")
    public ResponseEntity<List<Long>> interacoesUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.ok(usuarioService.solicitacoes(idUsuario));
    }

    @PostMapping("/foto-perfil/{id}/nova")
    public ResponseEntity<String> uploadFotoPerfil(@PathVariable Long id, @RequestParam("file") MultipartFile foto){
        String msg = usuarioService.uploadFoto(id, foto);
        if(msg.contains("sucesso")){
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    @PostMapping("/senha/alteracao")
    public ResponseEntity<?> alteraSenha(@RequestParam String novaSenha, @RequestParam Long id){
        int response = usuarioRepository.atualizaSenha(novaSenha, id);

        if(response > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/foto-perfil/{id}")
    public ResponseEntity<String> getFotoPerfil(@PathVariable Long id){
        String imagemBase64 = usuarioService.getFoto(id);

        if(imagemBase64.contains("não encontrado")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(imagemBase64);
        }
    }

}
