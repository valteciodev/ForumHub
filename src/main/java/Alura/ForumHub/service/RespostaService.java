package Alura.ForumHub.service;

import Alura.ForumHub.domain.resposta.Resposta;
import Alura.ForumHub.domain.resposta.dto.RespostaAtualizarDTO;
import Alura.ForumHub.domain.resposta.dto.RespostaDTO;
import Alura.ForumHub.domain.resposta.dto.RespostaDetalhadaDTO;
import Alura.ForumHub.domain.topico.Topico;
import Alura.ForumHub.domain.usuario.Usuario;
import Alura.ForumHub.repository.RespostaRepository;
import Alura.ForumHub.repository.TopicoRepository;
import Alura.ForumHub.repository.UsuarioRepository;
import Alura.ForumHub.infra.exception.ExceptionUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespostaDetalhadaDTO cadastrar( RespostaDTO dados) {
        var topico = validarTopico(dados.topicoId());
        var usuario = validarUsuario(dados.autorId());

        var resposta = new Resposta(dados, topico, usuario);
        respostaRepository.save(resposta);

        return new RespostaDetalhadaDTO(resposta);
    }

    public Page<RespostaDetalhadaDTO> listar(Pageable paginacao) {
        var respostas = respostaRepository.findAllByAtivoTrue(paginacao)
                .map(RespostaDetalhadaDTO::new);
        if (respostas.isEmpty()) {
            throw ExceptionUtil.notFound("Nenhuma resposta encontrada");
        }
        return respostas;
    }

    public RespostaDetalhadaDTO detalhar(Long id) {
        var resposta = respostaRepository.findById(id)
            .orElseThrow(() -> ExceptionUtil.notFound("Resposta não encontrada"));

        if (!resposta.isAtivo()) {
            throw ExceptionUtil.badRequest("Resposta inativa");
        }

        return new RespostaDetalhadaDTO(resposta);
    }

    public RespostaDetalhadaDTO atualizar(@Valid RespostaAtualizarDTO dados) {
        var resposta = respostaRepository.findById(dados.id())
            .orElseThrow(() -> ExceptionUtil.notFound("Resposta não encontrada"));

        if (!resposta.isAtivo()) {
            throw ExceptionUtil.badRequest("Resposta inativa");
        }

        var topico = validarTopico(dados.topicoId());

        if (!topico.getId().equals(resposta.getTopico().getId())) {
            throw ExceptionUtil.forbidden("Resposta não pertence ao tópico informado");
        }

        if (!topico.isStatus()) {
            throw ExceptionUtil.badRequest("Tópico já resolvido");
        }

        var usuario = validarUsuario(dados.autorId());

        if (!usuario.getId().equals(resposta.getAutor().getId())) {
            throw ExceptionUtil.forbidden("Resposta não pertence ao usuário informado");
        }

        resposta.atualizarInformacoes(dados);

        return new RespostaDetalhadaDTO(resposta);
    }

    private Usuario validarUsuario(Long usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> ExceptionUtil.notFound("Usuário não encontrado"));
        if (!usuario.isAtivo()) {
            throw ExceptionUtil.badRequest("Usuário inativo");
        }

        return usuario;
    }

    private Topico validarTopico(Long topicoId) {
        var topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> ExceptionUtil.notFound("Tópico não encontrado"));
        if (!topico.isAtivo()) {
            throw ExceptionUtil.badRequest("Tópico inativo");
        }

        return topico;
    }

    public void excluir(Long id) {
        var resposta = respostaRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.notFound("Resposta não encontrada"));

        if (!resposta.isAtivo()) {
            throw ExceptionUtil.badRequest("Resposta já está inativa");
        }

        resposta.excluir();
        respostaRepository.save(resposta);
    }
}
