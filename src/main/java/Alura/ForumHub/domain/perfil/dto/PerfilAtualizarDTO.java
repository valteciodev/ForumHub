package Alura.ForumHub.domain.perfil.dto;

import Alura.ForumHub.domain.perfil.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerfilAtualizarDTO (

        @NotNull (message = "O ID do perfil é obrigatório")
        Long id,
        @NotBlank (message = "O nome do perfil é obrigatório")
        String nome) {

    public PerfilAtualizarDTO(Perfil perfil) {
        this(perfil.getId(), perfil.getNome());
    }
}
