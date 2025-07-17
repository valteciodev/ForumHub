package Alura.ForumHub.domain.topico;

import Alura.ForumHub.domain.curso.Curso;
import Alura.ForumHub.domain.resposta.Resposta;
import Alura.ForumHub.domain.topico.dto.TopicoAtualizarDTO;
import Alura.ForumHub.domain.topico.dto.TopicoDTO;
import Alura.ForumHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;

    private boolean ativo;

    public Topico(TopicoDTO dados, Usuario usuario, Curso curso) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.status = true; // Por padrão, o tópico é criado com status 'true'
        this.autor = usuario;
        this.curso = curso;
    }

    public void atualizarInformacoes(TopicoAtualizarDTO dados, Curso curso) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = curso;
    }

    public void excluir() {
        this.ativo = false;
    }
}
