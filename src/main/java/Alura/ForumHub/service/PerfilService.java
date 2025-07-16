package Alura.ForumHub.service;

import Alura.ForumHub.domain.perfil.Perfil;
import Alura.ForumHub.domain.perfil.dto.PerfilAtualizarDTO;
import Alura.ForumHub.domain.perfil.dto.PerfilDTO;
import Alura.ForumHub.domain.perfil.dto.PerfilDetalhadoDTO;
import Alura.ForumHub.repository.PerfilRepository;
import Alura.ForumHub.service.exception.ValidacaoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private PerfilRepository perfilRepository;

    public Object cadastrar(PerfilDTO dados) {
        var perfil = new Perfil(dados);
        perfilRepository.save(perfil);
        return new PerfilDetalhadoDTO(perfil);
    }

    public Page<PerfilDetalhadoDTO> listar(Pageable paginacao) {
        var perfis = perfilRepository.findAllByAtivoTrue(paginacao)
                .map(PerfilDetalhadoDTO::new);
        return perfis;
    }

    public PerfilDetalhadoDTO detalhar(Long id) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        if (!perfil.isAtivo()) {
            throw new ValidacaoException("Perfil inativo");
        }
        return new PerfilDetalhadoDTO(perfil);
    }

    public Object atualizar(PerfilAtualizarDTO dados) {
        var perfil = perfilRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        perfil.atualizarInformacoes(dados);
        perfilRepository.save(perfil);
        return new PerfilDetalhadoDTO(perfil);
    }

    public void excluir(Long id) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        if (!perfil.isAtivo()) {
            throw new ValidacaoException("Perfil já está inativo");
        }
        perfil.excluir();
    }
}
