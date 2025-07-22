package Alura.ForumHub.controller;

import Alura.ForumHub.domain.usuario.dto.UsuarioAtualizarDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioAlterarRoleDTO;
import Alura.ForumHub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDTO dados, UriComponentsBuilder uriBuilder) {
        var usuario =  usuarioService.cadastrar(dados);
        
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(usuarioService.listar(paginacao));
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.detalhar(id));
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid UsuarioAtualizarDTO dados) {
        var usuario = usuarioService.atualizar(dados);
        return ResponseEntity.ok(usuario);
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/role")
    public ResponseEntity alterarRole(@RequestBody @Valid UsuarioAlterarRoleDTO dados) {
        var usuario = usuarioService.alterarRole(dados);
        return ResponseEntity.ok(usuario);
    }

}
