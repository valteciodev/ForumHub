package Alura.ForumHub.controller;

import Alura.ForumHub.domain.resposta.dto.RespostaDTO;
import Alura.ForumHub.service.RespostaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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





}
