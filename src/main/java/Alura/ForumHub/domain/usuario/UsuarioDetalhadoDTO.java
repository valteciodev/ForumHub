package Alura.ForumHub.domain.usuario;

import Alura.ForumHub.domain.perfil.PerfilDetalhadoDTO;

public record UsuarioDetalhadoDTO(
        Long id,
        String nome,
        String email,
        PerfilDetalhadoDTO perfil
        ) {
}
