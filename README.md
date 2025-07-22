# ForumHub

API REST para gerenciamento de fóruns de discussão, desenvolvida com Spring Boot, JWT, roles de acesso e documentação Swagger/OpenAPI.

## Funcionalidades
- Cadastro, autenticação e gerenciamento de usuários (USER/ADMIN)
- CRUD de cursos, tópicos, respostas e perfis
- Controle de acesso por perfil (USER/ADMIN)
- Paginação e ordenação de resultados
- Validações e tratamento de exceções customizadas
- Documentação interativa com Swagger/OpenAPI
- Migrations de banco de dados com Flyway
- Alteração de autorização (role) de usuário por ADMIN
- Endpoint para alteração de role de usuário (`PUT /usuarios/role`)
- Senhas salvas com BCrypt
- Exceções personalizadas para erros de negócio e validação
- Respostas padronizadas para erros HTTP (400, 404, 403, 500)

## Tecnologias
- Java 17+
- Spring Boot 3.x
- Spring Security (JWT)
- Spring Data JPA
- Flyway
- Swagger/OpenAPI (springdoc-openapi)
- Lombok

## Como rodar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/forumhub.git
   cd forumhub
   ```
2. Configure o banco de dados em `src/main/resources/application.properties`.
3. Execute as migrations (Flyway roda automaticamente ao subir a aplicação).
4. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acesse a documentação:
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints principais
- `POST /login` — Autenticação (JWT)
- `POST /usuarios` — Cadastro de novo usuário
- `GET /usuarios` — Listagem de usuários (ADMIN)
- `GET /cursos` — Listagem de cursos (autenticado)
- `POST /topicos` — Cadastro de tópico (autenticado)
- `POST /respostas` — Cadastro de resposta (autenticado)
- `PUT /usuarios/role` — Alteração de role de usuário (ADMIN)

## Segurança
- JWT para autenticação stateless
- Roles USER e ADMIN para controle de acesso
- Endpoints sensíveis protegidos por perfil

## Licença
[Apache License 2.0](LICENSE)

---
Desenvolvido por Valtecio Silva Almeida.
