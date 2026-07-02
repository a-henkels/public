# ADR-001: Injeção por construtor em vez de injeção por campo

**Data:** 2026-07-02
**Status:** aceito

## Contexto
Vindo de CDI (`@Inject` em campo era o padrão no projeto legado), é preciso
definir o estilo de injeção do projeto. O Spring aceita `@Autowired` em campo,
em setter ou injeção por construtor.

## Alternativas consideradas
- **Injeção por campo (`@Autowired` no atributo)** — menos linhas; porém campo
  não pode ser `final`, esconde as dependências da classe e obriga a subir
  container (ou usar reflexão) para testar.
- **Injeção por construtor** — dependências explícitas na assinatura, campos
  `final` (imutáveis), classe testável com `new` puro em teste de unidade.
  Com um único construtor, nem `@Autowired` é necessário.

## Decisão
Toda dependência entra por construtor. Nenhum `@Autowired` em campo no projeto.

## Consequências
Classes com muitas dependências ficam com construtores grandes — o que é
desejável: vira um alarme visual de que a classe acumulou responsabilidades
demais e deve ser dividida.
