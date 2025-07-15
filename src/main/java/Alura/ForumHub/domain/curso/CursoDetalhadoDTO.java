package Alura.ForumHub.domain.curso;

public record CursoDetalhadoDTO(
        Long id,
        String nome,
        Categoria categoria
        ) {
    public CursoDetalhadoDTO(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
