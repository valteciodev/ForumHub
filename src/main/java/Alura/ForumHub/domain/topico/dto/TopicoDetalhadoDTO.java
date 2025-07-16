package Alura.ForumHub.domain.topico.dto;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.resposta.dto.RespostaDetalhadaDTO;
import Alura.ForumHub.domain.topico.Topico;
import Alura.ForumHub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetalhadoDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        boolean status,
        Usuario autor,
        Curso curso,
        List<RespostaDetalhadaDTO> respostas

) {
    public TopicoDetalhadoDTO(Topico dados) {
        this(
                dados.getId(),
                dados.getTitulo(),
                dados.getMensagem(),
                dados.getDataCriacao(),
                dados.isStatus(),
                dados.getAutor(),
                dados.getCurso(),
                dados.getRespostas().stream()
                        .map(RespostaDetalhadaDTO::new)
                        .toList()
        );
    }
}
