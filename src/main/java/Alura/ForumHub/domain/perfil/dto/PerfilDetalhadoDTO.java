package Alura.ForumHub.domain.perfil.dto;

import Alura.ForumHub.domain.perfil.Perfil;

public record PerfilDetalhadoDTO (
        Long id,
        String nome,
        Long idUsuario
        ) {
    public PerfilDetalhadoDTO(Perfil perfil) {
        this(perfil.getId(), perfil.getNome(), perfil.getIdUsuario());
    }
}
