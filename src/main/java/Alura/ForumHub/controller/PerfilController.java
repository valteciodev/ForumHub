package Alura.ForumHub.controller;

import Alura.ForumHub.domain.perfil.PerfilAtualizarDTO;
import Alura.ForumHub.domain.perfil.PerfilDTO;
import Alura.ForumHub.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity cadastrar (PerfilDTO dados) {
        var dto = perfilService.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(perfilService.listar(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(Long id) {
        return ResponseEntity.ok(perfilService.detalhar(id));
    }

    @PutMapping
    public ResponseEntity atualizar(PerfilAtualizarDTO dados) {
        var perfil = perfilService.atualizar(dados);
        return ResponseEntity.ok(perfil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        perfilService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
