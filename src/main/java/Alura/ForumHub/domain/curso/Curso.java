    package Alura.ForumHub.domain.curso;

    import Alura.ForumHub.domain.curso.dto.CursoAtualizarDTO;
    import Alura.ForumHub.domain.curso.dto.CursoDTO;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    @Entity(name="Curso")
    @Table(name = "cursos")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Curso {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        @Enumerated(EnumType.STRING)
        private Categoria categoria;

        private boolean ativo;

        public Curso(CursoDTO dados) {
            this(null, dados.nome(), dados.categoria(), true);
        }

        public void atualizarInformacoes(CursoAtualizarDTO dados) {
            this.nome = dados.nome();
            this.categoria = dados.categoria();
        }

        public void excluir() {
            this.ativo = false;
        }
    }
