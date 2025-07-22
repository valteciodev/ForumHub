package Alura.ForumHub.service;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.curso.dto.CursoAtualizarDTO;
import Alura.ForumHub.domain.curso.dto.CursoDTO;
import Alura.ForumHub.domain.curso.dto.CursoDetalhadoDTO;
import Alura.ForumHub.repository.CursoRepository;
import Alura.ForumHub.infra.exception.ExceptionUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public CursoDetalhadoDTO cadastrar(CursoDTO dados) {
        if (cursoRepository.existsByNomeIgnoreCaseAndCategoriaAndAtivoTrue(dados.nome(), dados.categoria())) {
            throw ExceptionUtil.badRequest("Curso já cadastrado");
        }
        var curso = new Curso(dados);
        cursoRepository.save(curso);

        // Converto Curso para CursoDetalhadoDTO
        return new CursoDetalhadoDTO(curso);
    }

    public Page<CursoDetalhadoDTO> listar(Pageable paginacao) {
        var page = cursoRepository.findAllByAtivoTrue(paginacao)
                .map(CursoDetalhadoDTO::new);
        if (page.isEmpty()) {
            throw ExceptionUtil.notFound("Nenhum curso encontrado");
        }
        return page;
    }

    public CursoDetalhadoDTO detalhar(Long id) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.notFound("Curso não encontrado"));
        if (!curso.isAtivo()){
            throw ExceptionUtil.badRequest("Curso inativo");
        }
        return new CursoDetalhadoDTO(curso);
    }

    @Transactional
    public CursoDetalhadoDTO atualizar(@Valid CursoAtualizarDTO dados) {
        var curso = cursoRepository.findById(dados.id()).orElseThrow(() -> ExceptionUtil.notFound("Curso não encontrado"));

        if (!curso.isAtivo()) {
            throw ExceptionUtil.badRequest("Curso inativo, não é possível atualizar");
        }

        if (cursoRepository.existsByNomeIgnoreCaseAndCategoriaAndAtivoTrueAndIdNot(dados.nome(), dados.categoria(), curso.getId())) {
            throw ExceptionUtil.badRequest("Curso já cadastrado");
        }

        curso.atualizarInformacoes(dados);

        return new CursoDetalhadoDTO(curso);
    }

    @Transactional
    public void excluir(Long id) {
        var curso = cursoRepository.findById(id).orElseThrow(() -> ExceptionUtil.notFound("Curso não encontrado"));
        if (!curso.isAtivo()) {
            throw ExceptionUtil.badRequest("Curso já está inativo");
        }
        curso.excluir();
    }
}
