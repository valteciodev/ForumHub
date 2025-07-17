package Alura.ForumHub.repository;

import Alura.ForumHub.domain.topico.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloIgnoreCase(String titulo);
    Page<Topico> findAllByAtivoTrue(Pageable paginacao);
    boolean existsByTituloIgnoreCaseAndIdNot(String titulo, Long id);
}
