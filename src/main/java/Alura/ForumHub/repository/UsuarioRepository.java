package Alura.ForumHub.repository;

import Alura.ForumHub.domain.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailIgnoreCase(String email);
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    Usuario findByEmail(String subject);
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.perfil WHERE u.email = :email")
    Usuario findByEmailWithPerfil(String email);
}