package Alura.ForumHub.domain.usuario.dto;

import Alura.ForumHub.domain.perfil.dto.PerfilDetalhadoDTO;
import Alura.ForumHub.domain.usuario.Usuario;

public record UsuarioDetalhadoDTO(
        Long id,
        String nome,
        String email,
        String perfil
        ) {
        public UsuarioDetalhadoDTO(Usuario autor) {
            this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getPerfil().getNome());
        }
}
