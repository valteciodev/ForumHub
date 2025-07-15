package Alura.ForumHub.domain.perfil;

public record PerfilDetalhadoDTO (
        Long id,
        String nome
        ) {
    public PerfilDetalhadoDTO(Perfil perfil) {
        this(perfil.getId(), perfil.getNome());
    }
}
