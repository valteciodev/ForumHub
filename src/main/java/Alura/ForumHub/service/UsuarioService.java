package Alura.ForumHub.service;

import Alura.ForumHub.domain.perfil.Perfil;
import Alura.ForumHub.domain.usuario.Usuario;
import Alura.ForumHub.domain.usuario.dto.UsuarioAtualizarDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioDetalhadoDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioAlterarRoleDTO;
import Alura.ForumHub.domain.usuario.Role;
import Alura.ForumHub.infra.exception.ExceptionUtil;
import Alura.ForumHub.repository.PerfilRepository;
import Alura.ForumHub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public UsuarioDetalhadoDTO cadastrar(@Valid UsuarioDTO dados) {
        if (usuarioRepository.existsByEmailIgnoreCase(dados.email())) {
            throw ExceptionUtil.badRequest("Já existe um usuário com este email");
        }

        if (perfilRepository.existsByNomeIgnoreCase(dados.nomePerfil())) {
            throw ExceptionUtil.badRequest("Já existe um perfil com esse nome");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(new UsuarioDTO(dados.nome(), dados.email(), senhaCriptografada, dados.nomePerfil()));

        usuarioRepository.save(usuario);
        var perfil = new Perfil(dados.nomePerfil(), usuario.getId());
        usuario.incluirPerfil(perfil);
        perfilRepository.save(perfil);

        return new UsuarioDetalhadoDTO(usuario);
    }

    public Page<UsuarioDetalhadoDTO> listar (Pageable paginacao) {
        var usuarios = usuarioRepository.findAllByAtivoTrue(paginacao)
                .map(UsuarioDetalhadoDTO::new);

        if (usuarios.isEmpty()) {
            throw ExceptionUtil.notFound("Nenhum usuário encontrado");
        }

        return usuarios;
    }

    public UsuarioDetalhadoDTO detalhar(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.notFound("Usuário não encontrado"));

        if (!usuario.isAtivo()) {
            throw ExceptionUtil.badRequest("Usuário inativo");
        }

        return new UsuarioDetalhadoDTO(usuario);
    }

    @Transactional
    public UsuarioDetalhadoDTO atualizar(@Valid UsuarioAtualizarDTO dados) {
        var usuario = usuarioRepository.findById(dados.id())
                .orElseThrow(() -> ExceptionUtil.notFound("Usuário não encontrado"));

        if (!usuario.isAtivo()) {
            throw ExceptionUtil.badRequest("Usuário inativo");
        }

        if (usuarioRepository.existsByEmailIgnoreCaseAndIdNot(dados.email(), dados.id())) {
            throw ExceptionUtil.badRequest("Já existe um usuário com este email");
        }

        usuario.atualizarInformacoes(dados);

        return new UsuarioDetalhadoDTO(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.notFound("Usuário não encontrado"));

        if (!usuario.isAtivo()) {
            throw ExceptionUtil.badRequest("Usuário já está inativo");
        }

        usuario.excluir();
    }

    @Transactional
    public UsuarioDetalhadoDTO alterarRole(@Valid UsuarioAlterarRoleDTO dados) {
        var usuario = usuarioRepository.findById(dados.id())
                .orElseThrow(() -> ExceptionUtil.notFound("Usuário não encontrado"));
        usuario.setRole(dados.role());
        return new UsuarioDetalhadoDTO(usuario);
    }
}
