package Alura.ForumHub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,

        @NotNull(message = "O ID do autor é obrigatório")
        Long autorId,

        @NotNull(message = "O ID do curso é obrigatório")
        Long cursoId
        ) {
}
