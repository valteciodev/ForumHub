package Alura.ForumHub.controller;

import Alura.ForumHub.domain.perfil.dto.PerfilAtualizarDTO;
import Alura.ForumHub.service.PerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfis")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(perfilService.listar(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.detalhar(id));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid PerfilAtualizarDTO dados) {
        var perfil = perfilService.atualizar(dados);
        return ResponseEntity.ok(perfil);
    }

}
