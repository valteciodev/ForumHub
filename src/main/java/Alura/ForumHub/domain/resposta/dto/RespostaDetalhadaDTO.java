package Alura.ForumHub.domain.resposta.dto;

import Alura.ForumHub.domain.topico.dto.TopicoDetalhadoDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioDetalhadoDTO;

import java.util.Date;

public record RespostaDetalhadaDTO (
        Long id,
        String mensagem,
        Date dataCriacao,
        boolean solucao,
        TopicoDetalhadoDTO topico,
        UsuarioDetalhadoDTO autor
        ) {
}
