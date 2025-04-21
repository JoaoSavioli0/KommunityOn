package com.kommunityon.website.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kommunityon.website.dtos.RecompensaComIdUnicoDTO;
import com.kommunityon.website.dtos.ResgateRecompensaDTO;
import com.kommunityon.website.entities.Ecopoint;
import com.kommunityon.website.entities.Recompensa;
import com.kommunityon.website.entities.RecompensaUsuario;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.repositories.EcopointRepository;
import com.kommunityon.website.repositories.RecompensaRepository;
import com.kommunityon.website.repositories.RecompensaUsuarioRepository;
import com.kommunityon.website.repositories.UsuarioRepository;

@Service
public class RecompensaService {
    @Autowired
    RecompensaRepository recompensaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RecompensaUsuarioRepository recompensaUsuarioRepository;

    @Autowired
    EcopointRepository ecopointRepository;

    public String resgataRecompensaService(ResgateRecompensaDTO resgateRecompensaDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(resgateRecompensaDTO.getUsuarioId());
        Optional<Recompensa> recompensaOptional = recompensaRepository.findById(resgateRecompensaDTO.getRecompensaId());
        Optional<Ecopoint> ecopointOptional = ecopointRepository.findById(resgateRecompensaDTO.getUsuarioId());

        if(usuarioOptional.isPresent() && recompensaOptional.isPresent()){
            if(ecopointOptional.isEmpty()){
                return "Você não tem pontos suficientes para esta recompensa!";
            }
            Usuario usuario = usuarioOptional.get();
            Recompensa recompensa = recompensaOptional.get();
            Ecopoint ecopoint = ecopointOptional.get();

            if(ecopoint.getPontos() < recompensa.getPreco()){
                return "Você não tem pontos suficientes para esta recompensa!";
            }

            if(recompensa.getEstoque() <= 0){
                return "Recompensa indisponível para o resgate!";
            }

            RecompensaUsuario recompensaUsuario = new RecompensaUsuario(usuario, recompensa);

            recompensa.setEstoque(recompensa.getEstoque()-1);
            ecopoint.setPontos(ecopoint.getPontos()-recompensa.getPreco());

            recompensaUsuarioRepository.save(recompensaUsuario);

            return "Recompensa resgatada! Você pode acessar suas recompensas em \"Minhas Recompensas\"";
        }else{
            return "Erro ao resgatar recompensa, verificar usuário e recompensa";
        }
    }

    public List<RecompensaComIdUnicoDTO> recompensasUsuarioService(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();

            List<RecompensaUsuario> recompensasUsuario = recompensaUsuarioRepository.findAllByUsuario(usuario);

            return recompensasUsuario.stream().map(r -> new RecompensaComIdUnicoDTO(
                r.getId(), 
                r.getRecompensa().getId(), 
                r.getRecompensa().getNome(), 
                r.getRecompensa().getDescricao(),
                r.getRecompensa().getPreco(),
                r.getRecompensa().getEstoque(),
                r.isAtivado())).collect(Collectors.toList());
        }
        return null;
    }

    public String ativaRecompensaService(Long id){
        Optional<RecompensaUsuario> recompensaUsuarioOptional = recompensaUsuarioRepository.findById(id);

        if(recompensaUsuarioOptional.isPresent()){
            RecompensaUsuario recompensaUsuario = recompensaUsuarioOptional.get();
            
            recompensaUsuario.setAtivado(true);

            recompensaUsuarioRepository.save(recompensaUsuario);
            
            return "Sua recompensa foi ativada com sucesso!";
        }

        return "Erro: Recompensa não encontrada!";
    }
}
