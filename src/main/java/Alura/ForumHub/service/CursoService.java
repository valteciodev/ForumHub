package Alura.ForumHub.service;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.curso.dto.CursoAtualizarDTO;
import Alura.ForumHub.domain.curso.dto.CursoDTO;
import Alura.ForumHub.domain.curso.dto.CursoDetalhadoDTO;
import Alura.ForumHub.repository.CursoRepository;
import Alura.ForumHub.service.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoDetalhadoDTO cadastrar(CursoDTO dados) {
        if (cursoRepository.existsByNomeIgnoreCaseAndCategoriaAndAtivoTrue(dados.nome(), dados.categoria())) {
            throw new ValidacaoException("Curso já cadastrado");
        }
        var curso = new Curso(dados);
        cursoRepository.save(curso);

        // Converto Curso para CursoDetalhadoDTO
        return new CursoDetalhadoDTO(curso);
    }

    public Page<CursoDetalhadoDTO> listar(Pageable paginacao) {
        // Busco todos os cursos e converto para CursoDetalhadoDTO
        var page = cursoRepository.findAllByAtivoTrue(paginacao)
                .map(CursoDetalhadoDTO::new);
        // Verifico se a página está vazia
        if (page.isEmpty()) {
            throw new ValidacaoException("Nenhum curso encontrado");
        }
        return page;
    }

    public CursoDetalhadoDTO detalhar(Long id) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Curso não encontrado"));
        if (!curso.isAtivo()){
            throw new ValidacaoException("Curso inativo");
        }
        return new CursoDetalhadoDTO(curso);
    }

    public CursoDetalhadoDTO atualizar(@Valid CursoAtualizarDTO dados) {
        var curso = cursoRepository.findById(dados.id()).orElseThrow(() -> new ValidacaoException("Curso não encontrado"));

        if (!curso.isAtivo()) {
            throw new ValidacaoException("Curso inativo, não é possível atualizar");
        }

        if (cursoRepository.existsByNomeIgnoreCaseAndCategoriaAndAtivoTrueAndIdNot(dados.nome(), dados.categoria(), curso.getId())) {
            throw new ValidacaoException("Curso já cadastrado");
        }

        curso.atualizarInformacoes(dados);

        return new CursoDetalhadoDTO(curso);
    }

    public void excluir(Long id) {
        var curso = cursoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Curso não encontrado"));
        if (!curso.isAtivo()) {
            throw new ValidacaoException("Curso já está inativo");
        }
        curso.excluir();
    }
}
