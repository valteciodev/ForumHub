package Alura.ForumHub.domain.topico.dto;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.resposta.dto.RespostaDetalhadaDTO;
import Alura.ForumHub.domain.usuario.Usuario;

import java.util.Date;
import java.util.List;

public record TopicoDetalhadoDTO(
        Long id,
        String titulo,
        String mensagem,
        Date dataCriacao,
        boolean status,
        Usuario autor,
        Curso curso,
        List<RespostaDetalhadaDTO> respostas

) {
}
