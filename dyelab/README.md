# dyelab

API REST em **Spring Boot 3 / Java 21** modelando uma fatia do domínio de automação de tinturaria têxtil: a separação entre **receitas de tingimento** (lógica de processo pura) e **perfis de máquina** (capacidades físicas do equipamento), inspirada nos princípios de controle de batelada da **ISA-88 (IEC 61512)**.

Projeto de estudo com um objetivo explícito: portar para o ecossistema Spring um domínio que conheço a fundo do mundo Java EE — documentando as decisões no caminho.

## Rodando

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

```bash
# listar receitas
curl http://localhost:8080/api/receitas

# buscar por id
curl http://localhost:8080/api/receitas/1

# criar
curl -X POST http://localhost:8080/api/receitas \
  -H "Content-Type: application/json" \
  -d '{"codigo":"REC-010","descricao":"Tingimento reativo escuro","classeProcesso":"reativo"}'
```

Testes:

```bash
mvn test
```

## Estrutura

```
src/main/java/br/com/andre/dyelab/
├── DyeLabApplication.java   # ponto de entrada
└── receita/                    # pacote por FUNCIONALIDADE, não por camada
    ├── Receita.java
    ├── ReceitaService.java
    └── ReceitaController.java
adr/                            # Architecture Decision Records
```

> Organização por funcionalidade (package by feature) em vez de `controllers/`, `services/`, `models/`: mantém junto o que muda junto e deixa as fronteiras de domínio visíveis — o que importa quando se discute decomposição de um monolito.

## Decisões de arquitetura

Registradas em [`adr/`](adr/). Toda escolha não-óbvia do projeto tem um ADR curto explicando o contexto, as alternativas e o porquê.

## Roadmap

- [x] API REST com injeção de dependência e testes de unidade
- [ ] Persistência Oracle com Spring Data JPA
- [ ] Validação e tratamento de erros padronizado
- [ ] Autenticação (Spring Security) e profiles por ambiente
- [ ] Testes de integração com Testcontainers (Oracle)
- [ ] Documentação OpenAPI
- [ ] Entidade PerfilMaquina + regras de compatibilidade receita × máquina
- [ ] Docker Compose (app + Oracle) e pipeline CI
