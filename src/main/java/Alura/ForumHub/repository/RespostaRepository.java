package Alura.ForumHub.repository;

import Alura.ForumHub.domain.resposta.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Page<Resposta> findAllByAtivoTrue(Pageable paginacao);
}
