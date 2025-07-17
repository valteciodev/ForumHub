package Alura.ForumHub.service;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.topico.Topico;
import Alura.ForumHub.domain.topico.dto.TopicoAtualizarDTO;
import Alura.ForumHub.domain.topico.dto.TopicoDTO;
import Alura.ForumHub.domain.topico.dto.TopicoDetalhadoDTO;
import Alura.ForumHub.domain.usuario.Usuario;
import Alura.ForumHub.repository.CursoRepository;
import Alura.ForumHub.repository.TopicoRepository;
import Alura.ForumHub.repository.UsuarioRepository;
import Alura.ForumHub.service.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoDetalhadoDTO cadastrar(TopicoDTO dados) {
        var usuario = validarUsuario(dados.autorId());
        var curso = validarCurso(dados.cursoId());

        if (topicoRepository.existsByTituloIgnoreCase(dados.titulo())) {
            throw new ValidacaoException("Já existe um tópico com este título");
        }

        var topico = new Topico(dados, usuario, curso);
        topicoRepository.save(topico);

        return new TopicoDetalhadoDTO(topico);
    }

    public Page<TopicoDetalhadoDTO> listar(Pageable paginacao) {
        var topicos = topicoRepository.findAllByAtivoTrue(paginacao)
                .map(TopicoDetalhadoDTO::new);
        if (topicos.isEmpty()) {
            throw new ValidacaoException("Nenhum tópico encontrado");
        }
        return topicos;
    }

    public TopicoDetalhadoDTO detalhar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        if (!topico.isAtivo()) {
            throw new ValidacaoException("Tópico inativo");
        }
        return new TopicoDetalhadoDTO(topico);
    }

    public TopicoDetalhadoDTO atualizar(TopicoAtualizarDTO dados) {
        var topico = topicoRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        if (!topico.isAtivo()) {
            throw new ValidacaoException("Tópico inativo");
        }

        validarUsuario(dados.autorId());
        var curso = validarCurso(dados.cursoId());

        if (topicoRepository.existsByTituloIgnoreCaseAndIdNot(dados.titulo(), dados.id())) {
            throw new ValidacaoException("Já existe um tópico com este título");
        }
        topico.atualizarInformacoes(dados, curso);
        return new TopicoDetalhadoDTO(topico);
    }

    public void excluir(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        if (!topico.isAtivo()) {
            throw new ValidacaoException("Tópico já está inativo");
        }
        topico.excluir();
    }

    private Usuario validarUsuario(Long usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!usuario.isAtivo()) {
            throw new ValidacaoException("Usuário inativo");
        }
        return usuario;
    }

    private Curso validarCurso(Long cursoId) {
        var curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        if (!curso.isAtivo()) {
            throw new ValidacaoException("Curso inativo");
        }

        return curso;
    }

}
