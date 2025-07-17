package Alura.ForumHub.domain.resposta;

import Alura.ForumHub.domain.resposta.dto.RespostaAtualizarDTO;
import Alura.ForumHub.domain.resposta.dto.RespostaDTO;
import Alura.ForumHub.domain.topico.Topico;
import Alura.ForumHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    private boolean solucao;

    private boolean ativo;

    public Resposta(RespostaDTO dados, Topico topico, Usuario usuario) {
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.dataCriacao = LocalDateTime.now();
        this.autor = usuario;
        this.solucao = false;
        this.ativo = true;
    }

    public void atualizarInformacoes(RespostaAtualizarDTO dados) {
        this.mensagem = dados.mensagem();
    }

    public void excluir() {
        this.ativo = false;
    }
}
