package Alura.ForumHub.domain.curso.dto;

import Alura.ForumHub.domain.curso.Categoria;
import Alura.ForumHub.domain.curso.Curso;

public record CursoDetalhadoDTO(
        Long id,
        String nome,
        Categoria categoria
        ) {
    public CursoDetalhadoDTO(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
