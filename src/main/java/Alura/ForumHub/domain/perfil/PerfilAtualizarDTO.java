package Alura.ForumHub.domain.perfil;

public record PerfilAtualizarDTO (
    Long id,
    String nome
    ) {

    public PerfilAtualizarDTO(Perfil perfil) {
        this(perfil.getId(), perfil.getNome());
    }
}
