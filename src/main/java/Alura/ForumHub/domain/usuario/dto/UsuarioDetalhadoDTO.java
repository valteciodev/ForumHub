package Alura.ForumHub.domain.usuario.dto;

import Alura.ForumHub.domain.perfil.dto.PerfilDetalhadoDTO;

public record UsuarioDetalhadoDTO(
        Long id,
        String nome,
        String email,
        PerfilDetalhadoDTO perfil
        ) {
}
