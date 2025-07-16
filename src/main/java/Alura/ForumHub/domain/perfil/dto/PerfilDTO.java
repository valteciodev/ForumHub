package Alura.ForumHub.domain.perfil.dto;

import jakarta.validation.constraints.NotBlank;

public record PerfilDTO(
        @NotBlank(message = "O nome do perfil é obrigatório")
        String nome
        ) {
}
