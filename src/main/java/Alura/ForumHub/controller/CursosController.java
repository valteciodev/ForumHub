package Alura.ForumHub.controller;

import Alura.ForumHub.domain.curso.dto.CursoAtualizarDTO;
import Alura.ForumHub.domain.curso.dto.CursoDTO;
import Alura.ForumHub.service.CursoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CursoDTO dados) {
        var dto = cursoService.cadastrar(dados);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(cursoService.listar(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.detalhar(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody CursoAtualizarDTO dados) {
        var curso = cursoService.atualizar(dados);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
