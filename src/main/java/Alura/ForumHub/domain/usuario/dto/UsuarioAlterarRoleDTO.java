package Alura.ForumHub.domain.usuario.dto;

import Alura.ForumHub.domain.usuario.Role;
import jakarta.validation.constraints.NotNull;

public record UsuarioAlterarRoleDTO(
    @NotNull(message = "O ID do usuário é obrigatório")
    Long id,
    @NotNull(message = "A nova role é obrigatória")
    Role role
) {}

