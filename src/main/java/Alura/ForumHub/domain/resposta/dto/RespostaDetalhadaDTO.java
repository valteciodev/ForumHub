package Alura.ForumHub.domain.resposta.dto;

import Alura.ForumHub.domain.resposta.Resposta;
import Alura.ForumHub.domain.topico.dto.TopicoDetalhadoDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioDetalhadoDTO;

import java.util.Date;

public record RespostaDetalhadaDTO (
        Long id,
        String mensagem,
        Date dataCriacao,
        boolean solucao,
        Long topico_id,
        UsuarioDetalhadoDTO autor
        ) {
        public RespostaDetalhadaDTO(Resposta dados) {
            this(
                    dados.getId(),
                    dados.getMensagem(),
                    dados.getDataCriacao(),
                    dados.isSolucao(),
                    dados.getTopico().getId(),
                    new UsuarioDetalhadoDTO(dados.getAutor())
            );

        }
}
