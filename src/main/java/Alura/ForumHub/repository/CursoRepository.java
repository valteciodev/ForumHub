package Alura.ForumHub.repository;

import Alura.ForumHub.domain.curso.Categoria;
import Alura.ForumHub.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findAllByAtivoTrue(Pageable paginacao);
    boolean existsByNomeIgnoreCaseAndCategoriaAndAtivoTrue(String nome, Categoria categoria);
    boolean existsByNomeIgnoreCaseAndCategoriaAndAtivoTrueAndIdNot(String nome, Categoria categoria, Long id);
}
