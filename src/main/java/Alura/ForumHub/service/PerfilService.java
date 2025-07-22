package Alura.ForumHub.service;

import Alura.ForumHub.domain.perfil.dto.PerfilAtualizarDTO;
import Alura.ForumHub.domain.perfil.dto.PerfilDetalhadoDTO;
import Alura.ForumHub.repository.PerfilRepository;
import Alura.ForumHub.infra.exception.ExceptionUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Page<PerfilDetalhadoDTO> listar(Pageable paginacao) {
        var perfis = perfilRepository.findAllByAtivoTrue(paginacao)
                .map(PerfilDetalhadoDTO::new);
        if (perfis.isEmpty()) {
            throw ExceptionUtil.notFound("Nenhum perfil encontrado");
        }
        return perfis;
    }

    public PerfilDetalhadoDTO detalhar(Long id) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.notFound("Perfil não encontrado"));
        if (!perfil.isAtivo()) {
            throw ExceptionUtil.badRequest("Perfil inativo");
        }
        return new PerfilDetalhadoDTO(perfil);
    }

    @Transactional
    public PerfilDetalhadoDTO atualizar(PerfilAtualizarDTO dados) {

        var perfil = perfilRepository.findById(dados.id())
                .orElseThrow(() -> ExceptionUtil.notFound("Perfil não encontrado"));

        if (!perfil.isAtivo()) {
            throw ExceptionUtil.badRequest("Perfil inativo");
        }

        if(perfilRepository.existsByNomeIgnoreCaseAndIdNot(dados.nome(), perfil.getId())) {
            throw ExceptionUtil.badRequest("Já existe um perfil com esse nome");
        }

        perfil.atualizarInformacoes(dados);
        return new PerfilDetalhadoDTO(perfil);
    }

}
