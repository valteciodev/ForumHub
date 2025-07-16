package Alura.ForumHub.domain.perfil.dto;

import Alura.ForumHub.domain.perfil.Perfil;

public record PerfilAtualizarDTO (
    Long id,
    String nome
    ) {

    public PerfilAtualizarDTO(Perfil perfil) {
        this(perfil.getId(), perfil.getNome());
    }
}
