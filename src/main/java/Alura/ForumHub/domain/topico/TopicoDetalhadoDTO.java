package Alura.ForumHub.domain.topico;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.resposta.RespostaDetalhadaDTO;
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
