package Alura.ForumHub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
        @NotBlank(message = "O nome do curso é obirgatótio")
        String nome,
        @NotNull(message = "A categoria é obrigatória")
        Categoria categoria
        ) {
}
