package Alura.ForumHub.domain.topico.dto;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.curso.dto.CursoDetalhadoDTO;
import Alura.ForumHub.domain.resposta.dto.RespostaDetalhadaDTO;
import Alura.ForumHub.domain.topico.Topico;
import Alura.ForumHub.domain.usuario.Usuario;
import Alura.ForumHub.domain.usuario.dto.UsuarioDetalhadoDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetalhadoDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        boolean status,
        UsuarioDetalhadoDTO autor,
        CursoDetalhadoDTO curso,
        List<RespostaDetalhadaDTO> respostas

) {
    public TopicoDetalhadoDTO(Topico dados) {
        this(
                dados.getId(),
                dados.getTitulo(),
                dados.getMensagem(),
                dados.getDataCriacao(),
                dados.isStatus(),
                new UsuarioDetalhadoDTO(dados.getAutor()),
                new CursoDetalhadoDTO(dados.getCurso()),
                dados.getRespostas() == null
                ? List.of()
                : dados.getRespostas().stream()
                .map(RespostaDetalhadaDTO::new)
                .toList()
        );
    }
}
