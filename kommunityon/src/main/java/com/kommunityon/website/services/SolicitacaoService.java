package com.kommunityon.website.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kommunityon.website.dtos.NewSolicitacaoDTO;
import com.kommunityon.website.dtos.SolicitacaoDTO;
import com.kommunityon.website.entities.Solicitacao;
import com.kommunityon.website.entities.SolicitacaoTag;
import com.kommunityon.website.entities.Tag;
import com.kommunityon.website.entities.Usuario;
import com.kommunityon.website.entities.UsuarioLikeSolicitacao;
import com.kommunityon.website.repositories.ComentarioRepository;
import com.kommunityon.website.repositories.SolicitacaoRepository;
import com.kommunityon.website.repositories.SolicitacaoTagRepository;
import com.kommunityon.website.repositories.TagRepository;
import com.kommunityon.website.repositories.UsuarioLikeSolicitacaoRepository;
import com.kommunityon.website.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class SolicitacaoService {
    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioLikeSolicitacaoRepository usuarioLikeSolicitacaoRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    SolicitacaoTagRepository solicitacaoTagRepository;

    @Autowired
    TagRepository tagRepository;

    public List<SolicitacaoDTO> solicitacoes(List<Integer> tagId) {
        if (tagId.isEmpty()) {
            Optional<List<SolicitacaoDTO>> solicitacoes = solicitacaoRepository.findAllSemFiltro();
            if (solicitacoes.isPresent()) {
                return solicitacoes.get();
            } else {
                return new ArrayList<>();
            }
        } else {
            Optional<List<SolicitacaoDTO>> solicitacoes = solicitacaoRepository.findAllPorTag(tagId);
            if (solicitacoes.isPresent()) {
                return solicitacoes.get();
            } else {
                return new ArrayList<>();
            }
        }
    }


    public List<SolicitacaoDTO> solicitacoesUsuario(Long id, List<Integer> tagId) {
        if (tagId.isEmpty()) {
            Optional<List<SolicitacaoDTO>> solicitacoes = solicitacaoRepository.findAllPorUsuario(id);
            if (solicitacoes.isPresent()) {
                return solicitacoes.get();
            } else {
                return new ArrayList<>();
            }
        } else {
            Optional<List<SolicitacaoDTO>> solicitacoes = solicitacaoRepository.findAllPorUsuarioComTag(id, tagId);
            if (solicitacoes.isPresent()) {
                return solicitacoes.get();
            } else {
                return new ArrayList<>();
            }
        }
    }

    public Optional<Solicitacao> solicitacaoPorId(Long id) {
        Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
        return solicitacao;
    }

    public void atualizarNumComentarios(int numComentarios, Long id) {
        solicitacaoRepository.incrementaComentario(numComentarios, id);
    }

    public Solicitacao registrar(NewSolicitacaoDTO newSolicitacaoDTO) {
        ArrayList<Solicitacao> solicitacoesAbertas = solicitacaoAberta(newSolicitacaoDTO.getIdUsuario());
        Boolean existeSolicitacaoComLikesInsuficientes = solicitacoesAbertas.stream().anyMatch(s -> s.getNumLikes() < 20);

        if (existeSolicitacaoComLikesInsuficientes) {
            return null;
        }

        Optional<Usuario> usuario = usuarioRepository.findById(newSolicitacaoDTO.getIdUsuario());
        Solicitacao solicitacao = new Solicitacao();

        if (usuario.isPresent()) {
            solicitacao.setBairro(newSolicitacaoDTO.getBairro());
            solicitacao.setDescricao(newSolicitacaoDTO.getDescricao());
            solicitacao.setTitulo(newSolicitacaoDTO.getTitulo());
            solicitacao.setAnonimo(newSolicitacaoDTO.getAnonimo());
            solicitacao.setUsuario(usuario.get());
        }

        Solicitacao solicitacaoCadastrada = solicitacaoRepository.save(solicitacao);

        defineTags(solicitacaoCadastrada.getDescricao(), solicitacaoCadastrada.getTitulo(), solicitacaoCadastrada.getId());

        return solicitacaoCadastrada;
    }

    public void defineTags(String descricao, String titulo, Long idSolicitacao) {

        List<String> palavrasTransito = Arrays.asList("trânsito", "transito", "carro", "moto", "caminhões", "caminhoes",
                "semaforo", "semáforo", "lombada", "acidente", "engarrafamento", "ciclovia", "bicicleta", "ruas",
                "avenidas");

        List<String> palavrasSaude = Arrays.asList("hospital", "ubs", "uti", "saude", "saúde", "remedio", "remédio",
                "farmacia", "farmácia", "doente", "medico", "médico", "postinho", "vacina");

        List<String> palavrasViasDeTransito = Arrays.asList("estrada", "rodovia", "pavimento", "faixa de pedestre",
                "sinalização", "sinalizacao", "sinal", "placa", "cruzamento", "via", "balsa", "túnel", "tunel", "ponte",
                "avenida", "semáforo", "semaforo", "mão única", "mao unica", "mão dupla", "mao dupla", "dobradura",
                "faixa", "pedágio", "pedagio");

        List<String> palavrasEnergia = Arrays.asList("energia", "luz", "elétrica", "eletrica", "iluminação",
                "iluminacao", "rede elétrica", "rede eletrica", "gerador", "energia solar", "energia renovável",
                "energia renovavel", "eletricidade", "falta de energia", "instalação elétrica", "instalaçao eletrica",
                "fios", "transformador", "geração de energia", "gerador", "fornecimento de energia");

        List<String> palavrasInclusao = Arrays.asList("acessibilidade", "inclusão", "inclusao", "deficiência",
                "deficiencia", "cadeirante", "deficiente", "surdo", "cego", "comunidade", "lgbt", "gay", "homossexual",
                "bissexual", "transsexual", "igualdade", "educação inclusiva", "mobilidade reduzida", "idioma",
                "linguagem de sinais", "diversidade", "exclusão social", "emprego inclusivo");

        List<String> palavrasViolencia = Arrays.asList("violência", "violencia", "agressão", "agressao", "assalto",
                "furto", "roubo", "homicídio", "homicidio", "pedofilia", "assassinato", "bullying", "sequestro",
                "abuso", "abusador", "estupro", "estuprador", "agressor", "vítima", "vitima", "discriminação",
                "discriminaçao", "tráfico", "trafico", "criminoso", "ataque", "violência doméstica", "ameaça", "crime");

        List<String> palavrasEntretenimento = Arrays.asList("cinema", "filme", "série", "serie", "música", "musica",
                "show", "evento", "teatro", "dança", "cultura", "livros", "entrevista", "esporte", "jogo", "diversão",
                "vídeo", "clipe", "artista", "comédia", "festival");

        List<String> palavrasServicosPublicos = Arrays.asList("saneamento", "limpeza", "energia", "água", "agua",
                "esgoto", "coleta", "lixo", "tratamento de água", "desentupimento", "vigilância sanitária",
                "infraestrutura", "prefeitura", "postagem", "correios", "rua", "fiscalização", "taxi",
                "transporte público", "iluminação pública", "abastecimento", "onibus", "prefeito", "vereador",
                "vereadores", "seguranca", "segurança", "educacao", "educação", "educaçao", "crime");

        ArrayList<Integer> tagsEncontradas = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        sb.append(titulo);
        sb.append(" ");
        sb.append(descricao);

        String textoCompleto = sb.toString().toLowerCase();

        System.out.println(textoCompleto);

        if (containsAny(textoCompleto, palavrasTransito))
            tagsEncontradas.add(1);
        if (containsAny(textoCompleto, palavrasSaude))
            tagsEncontradas.add(2);
        if (containsAny(textoCompleto, palavrasViasDeTransito))
            tagsEncontradas.add(3);
        if (containsAny(textoCompleto, palavrasEnergia))
            tagsEncontradas.add(4);
        if (containsAny(textoCompleto, palavrasInclusao))
            tagsEncontradas.add(5);
        if (containsAny(textoCompleto, palavrasViolencia))
            tagsEncontradas.add(6);
        if (containsAny(textoCompleto, palavrasEntretenimento))
            tagsEncontradas.add(7);
        if (containsAny(textoCompleto, palavrasServicosPublicos))
            tagsEncontradas.add(8);

        registraTags(tagsEncontradas, idSolicitacao);
    }

    public static boolean containsAny(String texto, List<String> chaves) {
        return chaves.stream().anyMatch(texto::contains);
    }

    public void registraTags(ArrayList<Integer> tagIds, Long idSolicitacao) {
        for (int id : tagIds) {
            Tag tag = tagRepository.findById(Long.valueOf(id)).get();
            solicitacaoTagRepository.save(new SolicitacaoTag(idSolicitacao, tag));
        }
    }

    public List<Tag> tags(Long idSolicitacao) {
        List<SolicitacaoTag> tagsEncontradas = solicitacaoTagRepository.findBySolicitacaoId(idSolicitacao);
        return tagsEncontradas.stream()
                .map(SolicitacaoTag::getTag)
                .collect(Collectors.toList());
    }

    public String curtir(Long idUsuario, Long idSolicitacao) {
        Optional<Usuario> encontraUsuario = usuarioRepository.findById(idUsuario);
        Optional<Solicitacao> encontraSolicitacao = solicitacaoRepository.findById(idSolicitacao);

        if (encontraUsuario.isPresent() && encontraSolicitacao.isPresent()) {

            Usuario usuario = encontraUsuario.get();
            Solicitacao solicitacao = encontraSolicitacao.get();

            if (solicitacao.getUsuario().getId().equals(idUsuario)) {
                return "Você não pode curtir a própria solicitação!";
            }

            if (solicitacao.getDataConclusao() != null) {
                return "Você não pode curtir solicitações concluídas!";
            }

            List<Long> solicitacoesInteragidas = usuarioLikeSolicitacaoRepository
                    .findSolicitacaoIdsByUsuario(idUsuario);

            for (Long id : solicitacoesInteragidas) {
                if (id.equals(idSolicitacao)) {
                    return "Você já curtiu essa solicitação!";
                }
            }

            LocalDateTime dataInteracao1 = usuario.getDataInteracao1();
            LocalDateTime dataInteracao2 = usuario.getDataInteracao2();
            LocalDateTime dataAtual = LocalDateTime.now();

            Long tempoDecorridoInteracao1 = (dataInteracao1 != null)
                    ? ChronoUnit.DAYS.between(dataInteracao1, dataAtual)
                    : Long.MAX_VALUE;
            Long tempoDecorridoInteracao2 = (dataInteracao2 != null)
                    ? ChronoUnit.DAYS.between(dataInteracao2, dataAtual)
                    : Long.MAX_VALUE;

            if (dataInteracao1 == null || tempoDecorridoInteracao1 >= 7) {
                usuarioRepository.atualizarDataInteracao1(idUsuario, dataAtual);
                solicitacaoRepository.incrementaLike(idSolicitacao);
                salvaInteracao(usuario, solicitacao);
                return "Sucesso! Sua curtida foi cadastrada no dia: " + dataAtual.toLocalDate();
            } else if (dataInteracao2 == null || tempoDecorridoInteracao2 >= 7) {
                usuarioRepository.atualizarDataInteracao2(idUsuario, dataAtual);
                solicitacaoRepository.incrementaLike(idSolicitacao);
                salvaInteracao(usuario, solicitacao);
                return "Sucesso! Sua curtida foi cadastrada no dia: " + dataAtual.toLocalDate();
            }

            return "Você não tem interações disponíveis, espere "
                    + (7 - ((tempoDecorridoInteracao1 > tempoDecorridoInteracao2) ? tempoDecorridoInteracao1
                            : tempoDecorridoInteracao2))
                    + " dias para interagir novamente.";
        }

        return "Ocorreu um erro ao interagir com a solicitação.";
    }

    public void salvaInteracao(Usuario usuario, Solicitacao solicitacao) {
        UsuarioLikeSolicitacao usuarioLikeSolicitacao = new UsuarioLikeSolicitacao();
        usuarioLikeSolicitacao.setUsuario(usuario);
        usuarioLikeSolicitacao.setSolicitacao(solicitacao);
        usuarioLikeSolicitacaoRepository.save(usuarioLikeSolicitacao);
    }

    @Transactional
    public void excluiSolicitacao(Long id) {
        usuarioLikeSolicitacaoRepository.deleteBySolicitacaoId(id);
        comentarioRepository.deleteBySolicitacaoId(id);
        solicitacaoRepository.deleteById(id);
    }

    @Transactional
    public void concluiSolictacao(Long id) {
        LocalDateTime dataConclusao = LocalDateTime.now();
        solicitacaoRepository.concluiSolicitacao(dataConclusao, id);
    }

    public ArrayList<Solicitacao> solicitacaoAberta(Long idUsuario) {
        return solicitacaoRepository.verificaSolicitacaoAberta(idUsuario).get();
    }
}
