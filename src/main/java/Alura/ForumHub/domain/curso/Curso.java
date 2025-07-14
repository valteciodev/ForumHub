    package Alura.ForumHub.domain.curso;

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

    }
