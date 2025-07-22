package Alura.ForumHub.domain.perfil;

import Alura.ForumHub.domain.perfil.dto.PerfilAtualizarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Perfil")
@Table(name = "perfis")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private boolean ativo;

    private Long idUsuario;

    public Perfil(String nome, Long idUsuario) {
        this.nome = nome;
        this.ativo = true;
        this.idUsuario = idUsuario;
    }

    public void atualizarInformacoes(PerfilAtualizarDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }

    public void excluir() {
        this.ativo= false;
    }
}
