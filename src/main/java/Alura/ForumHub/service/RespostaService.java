package Alura.ForumHub.service;

import Alura.ForumHub.domain.resposta.Resposta;
import Alura.ForumHub.domain.resposta.dto.RespostaDTO;
import Alura.ForumHub.domain.resposta.dto.RespostaDetalhadaDTO;
import Alura.ForumHub.repository.RespostaRepository;
import Alura.ForumHub.repository.TopicoRepository;
import Alura.ForumHub.repository.UsuarioRepository;
import Alura.ForumHub.service.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public RespostaDetalhadaDTO cadastrar( RespostaDTO dados) {
        var topico = topicoRepository.findById(dados.topicoId())
            .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));

        if (!usuarioRepository.existsById(dados.autorId())) {
            throw new ValidacaoException("Usuário não encontrado");
        }

        if (!topico.isStatus()) {
            throw new ValidacaoException("Tópico já resolvido");
        }

        var resposta = new Resposta(dados);
        respostaRepository.save(resposta);

        return new RespostaDetalhadaDTO(resposta);
    }
}
