package Alura.ForumHub.controller;

import Alura.ForumHub.domain.resposta.dto.RespostaAtualizarDTO;
import Alura.ForumHub.domain.resposta.dto.RespostaDTO;
import Alura.ForumHub.service.RespostaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid RespostaDTO dados, UriComponentsBuilder uriBuilder) {
        var resposta = respostaService.cadastrar(dados);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.id()).toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity listar(Pageable paginacao) {
        return ResponseEntity.ok(respostaService.listar(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.detalhar(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid RespostaAtualizarDTO dados) {
        var resposta = respostaService.atualizar(dados);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        respostaService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
