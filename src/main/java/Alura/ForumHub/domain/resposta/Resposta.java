package Alura.ForumHub.domain.resposta;

import Alura.ForumHub.domain.resposta.dto.RespostaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="Resposta")
@Table(name = "respostas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @Column(name = "topico_id", nullable = false)
    private Long idTopico;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @Column(name = "autor_id", nullable = false)
    private Long idAutor;

    private boolean solucao;

    public Resposta(RespostaDTO dados) {
        this.mensagem = dados.mensagem();
        this.idTopico = dados.topicoId();
        this.dataCriacao = LocalDateTime.now();
        this.idAutor = dados.autorId();
        this.solucao = false;
    }
}
