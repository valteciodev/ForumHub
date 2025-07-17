package Alura.ForumHub.domain.resposta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaAtualizarDTO(
        @NotNull(message = "O ID da resposta é obrigatório")
        Long id,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,

        @NotNull(message = "O ID do tópico é obrigatório")
        Long topicoId,

        @NotNull(message = "O ID do autor é obrigatório")
        Long autorId

        ) {
}
