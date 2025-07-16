package Alura.ForumHub.service;

import Alura.ForumHub.domain.topico.Topico;
import Alura.ForumHub.domain.topico.dto.TopicoDTO;
import Alura.ForumHub.domain.topico.dto.TopicoDetalhadoDTO;
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
        var usuario = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }
        var curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        if (!curso.isAtivo()) {
            throw new RuntimeException("Curso inativo");
        }

        if (topicoRepository.existsByTituloIgnoreCase(dados.titulo())) {
            throw new RuntimeException("Já existe um tópico com este título");
        }

        var topico = new Topico(dados, usuario, curso);
        topicoRepository.save(topico);

        return new TopicoDetalhadoDTO(topico);
    }

    public Page<TopicoDetalhadoDTO> listar(Pageable paginacao) {
        var topicos = topicoRepository.findAllByAtivoTrue(paginacao)
                .map(TopicoDetalhadoDTO::new);
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
}
