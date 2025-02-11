package com.kommunityon.website;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioLikeSolicitacaoRepository usuarioLikeSolicitacaoRepository;

    public Optional<List<SolicitacaoDTO>> solicitacoes(String filtro){
        switch(filtro){
            case "por_data" -> {
                return solicitacaoRepository.findAllPorData();
            }
            case "por_like" -> {
                return solicitacaoRepository.findAllPorLike();
            }
        }
        return Optional.empty();
    }

    public Optional<List<Solicitacao>> solicitacoesUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return solicitacaoRepository.findByUsuario(usuario.get());
    }

    public Optional<Solicitacao> solicitacaoPorId(Long id){
        Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
        return solicitacao;
    }

    public Solicitacao registrar(NewSolicitacaoDTO newSolicitacaoDTO){
        Optional<Long> solicitacaoAberta = solicitacaoAberta(newSolicitacaoDTO.getIdUsuario());

        if(solicitacaoAberta.isPresent()){
            return null;
        }

        Optional<Usuario> usuario = usuarioRepository.findById(newSolicitacaoDTO.getIdUsuario());      
        Solicitacao solicitacao = new Solicitacao();

        if(usuario.isPresent()){
            solicitacao.setBairro(newSolicitacaoDTO.getBairro());
            solicitacao.setDescricao(newSolicitacaoDTO.getDescricao());
            solicitacao.setTitulo(newSolicitacaoDTO.getTitulo());
            solicitacao.setUsuario(usuario.get());
        }
        return solicitacaoRepository.save(solicitacao);
    }

    public String curtir(Long idUsuario, Long idSolicitacao){
        Optional<Usuario> encontraUsuario = usuarioRepository.findById(idUsuario);
        Optional<Solicitacao> encontraSolicitacao = solicitacaoRepository.findById(idSolicitacao);

        if(encontraUsuario.isPresent() && encontraSolicitacao.isPresent()){

            Usuario usuario = encontraUsuario.get();
            Solicitacao solicitacao = encontraSolicitacao.get();

            if(solicitacao.getUsuario().getId().equals(idUsuario)){
                return "Você não pode curtir a própria solicitação!";
            }

            List<Long> solicitacoesInteragidas = usuarioLikeSolicitacaoRepository.findSolicitacaoIdsByUsuario(idUsuario);
            
            for(Long id : solicitacoesInteragidas){
                if(id.equals(idSolicitacao)){
                    return "Você já curtiu essa solicitação!";
                }
            }

            LocalDateTime dataInteracao1 = usuario.getDataInteracao1();
            LocalDateTime dataInteracao2 = usuario.getDataInteracao2();
            LocalDateTime dataAtual = LocalDateTime.now();

            Long tempoDecorridoInteracao1 = (dataInteracao1 != null) ? ChronoUnit.DAYS.between(dataInteracao1, dataAtual) : Long.MAX_VALUE;
            Long tempoDecorridoInteracao2 = (dataInteracao2 != null) ? ChronoUnit.DAYS.between(dataInteracao2, dataAtual) : Long.MAX_VALUE;

            if(dataInteracao1 == null || tempoDecorridoInteracao1 >= 7){
                usuarioRepository.atualizarDataInteracao1(idUsuario, dataAtual);
                solicitacaoRepository.incrementaLike(idSolicitacao);
                salvaInteracao(usuario, solicitacao);
                return "Sucesso! Sua curtida foi cadastrada no dia: " + dataAtual.toLocalDate();
            }else if(dataInteracao2 == null || tempoDecorridoInteracao2 >= 7){
                usuarioRepository.atualizarDataInteracao2(idUsuario, dataAtual);
                solicitacaoRepository.incrementaLike(idSolicitacao);
                salvaInteracao(usuario, solicitacao);
                return "Sucesso! Sua curtida foi cadastrada no dia: " + dataAtual.toLocalDate();
            }

            return "Você não tem interações disponíveis, espere " + (7-((tempoDecorridoInteracao1 > tempoDecorridoInteracao2) ? tempoDecorridoInteracao1 : tempoDecorridoInteracao2)) + " dias para interagir novamente.";
        }

        return "Ocorreu um erro ao interagir com a solicitação.";
    }

    public void salvaInteracao(Usuario usuario, Solicitacao solicitacao){
        UsuarioLikeSolicitacao usuarioLikeSolicitacao = new UsuarioLikeSolicitacao();
        usuarioLikeSolicitacao.setUsuario(usuario);
        usuarioLikeSolicitacao.setSolicitacao(solicitacao);
        usuarioLikeSolicitacaoRepository.save(usuarioLikeSolicitacao);
    }

    public Optional<Long> solicitacaoAberta(Long idUsuario){
        return solicitacaoRepository.verificaSolicitacaoAberta(idUsuario);
    }
}
