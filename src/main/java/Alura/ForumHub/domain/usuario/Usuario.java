package Alura.ForumHub.domain.usuario;

import Alura.ForumHub.domain.perfil.Perfil;
import Alura.ForumHub.domain.usuario.dto.UsuarioAtualizarDTO;
import Alura.ForumHub.domain.usuario.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name="Usuario")
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    @JsonIgnore
    private Perfil perfil;

    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario(UsuarioDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.ativo = true;
        this.role = Role.USER;
    }

    public void incluirPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void atualizarInformacoes(UsuarioAtualizarDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void excluir() {
        this.ativo = false;
        this.perfil.excluir();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

