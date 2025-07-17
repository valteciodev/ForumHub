package Alura.ForumHub.controller;

import Alura.ForumHub.domain.perfil.dto.PerfilAtualizarDTO;
import Alura.ForumHub.domain.perfil.dto.PerfilDTO;
import Alura.ForumHub.service.PerfilService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrar (@RequestBody @Valid PerfilDTO dados, UriComponentsBuilder uriBuilder) {
//        var dto = perfilService.cadastrar(dados);
//        var uri = uriBuilder.path("/perfis/{id}").buildAndExpand(dto.id()).toUri();
//
//        // Retorna o status 201 Created com o local do novo recurso no cabeçalho Location
//        return ResponseEntity.created(uri).body(dto);
//    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(perfilService.listar(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.detalhar(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PerfilAtualizarDTO dados) {
        var perfil = perfilService.atualizar(dados);
        return ResponseEntity.ok(perfil);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity excluir(@PathVariable Long id) {
//        perfilService.excluir(id);
//        return ResponseEntity.noContent().build();
//    }

}
