package Alura.ForumHub.infra.security;

import Alura.ForumHub.domain.usuario.Usuario;
import Alura.ForumHub.repository.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String gerarToken (Usuario usuario) {
        System.out.println("Iniciando a geração do token JWT para o usuário: " + usuario.getEmail());
        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário não está ativo");
        }
        System.out.println("Verificando se o usuário está ativo: " + usuario.isAtivo());

        try {
            System.out.println("Gerando token JWT para o usuário: " + usuario.getEmail());
            var algoritimo = Algorithm.HMAC256(secret);
            System.out.println("Algoritmo de assinatura configurado com o segredo: " + secret);
            return JWT.create()
                    .withIssuer("API ForumHub")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Ocorreu um erro na geração do token", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("API ForumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
