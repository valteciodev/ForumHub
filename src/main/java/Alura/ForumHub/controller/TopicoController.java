package Alura.ForumHub.controller;

import Alura.ForumHub.domain.topico.dto.TopicoAtualizarDTO;
import Alura.ForumHub.domain.topico.dto.TopicoDTO;
import Alura.ForumHub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid TopicoDTO dados, UriComponentsBuilder uriBuilder) {
        var dto = topicoService.cadastrar(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(dto.id()).toUri();

        // Retorna o status 201 Created com o local do novo recurso no cabeçalho Location
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault (size = 10, page = 0, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable paginacao) {
        return ResponseEntity.ok(topicoService.listar(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoService.detalhar(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid TopicoAtualizarDTO dados) {
        var topico = topicoService.atualizar(dados);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        topicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
