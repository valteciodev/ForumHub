package Alura.ForumHub.repository;

import Alura.ForumHub.domain.perfil.Perfil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Page<Perfil> findAllByAtivoTrue(Pageable paginacao);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);
}
