package Alura.ForumHub.domain.resposta;

import Alura.ForumHub.domain.topico.TopicoDetalhadoDTO;
import Alura.ForumHub.domain.usuario.UsuarioDetalhadoDTO;

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
