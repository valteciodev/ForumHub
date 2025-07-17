package Alura.ForumHub.domain.resposta.dto;

import Alura.ForumHub.domain.resposta.Resposta;

import java.time.LocalDateTime;

public record RespostaDetalhadaDTO (
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        boolean solucao,
        Long idTopico,
        Long idAutor
        ) {
        public RespostaDetalhadaDTO(Resposta dados) {
            this(
                    dados.getId(),
                    dados.getMensagem(),
                    dados.getDataCriacao(),
                    dados.isSolucao(),
                    dados.getIdTopico(),
                    dados.getIdAutor()
            );

        }
}
