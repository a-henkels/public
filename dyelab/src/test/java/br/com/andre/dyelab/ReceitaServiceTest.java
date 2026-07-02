package br.com.andre.dyelab;

import br.com.andre.dyelab.receita.Receita;
import br.com.andre.dyelab.receita.ReceitaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Primeiro teste do projeto — de propósito, um teste de UNIDADE PURO:
 * repare que não há @SpringBootTest, nenhum container sobe, é "new" e pronto.
 * Roda em milissegundos. Essa é a base da pirâmide de testes (Semana 7).
 *
 * Isso só é possível porque a classe usa injeção por construtor —
 * o argumento prático da escolha feita no Controller.
 *
 * SEMANA 5: expandir com Mockito (mockar o repository quando ele existir).
 * SEMANA 6: adicionar testes de integração com @SpringBootTest + Testcontainers.
 */
class ReceitaServiceTest {

    @Test
    void deveSalvarGerandoId() {
        ReceitaService service = new ReceitaService();

        Receita nova = new Receita(null, "REC-999", "Teste", "reativo");
        Receita salva = service.salvar(nova);

        assertNotNull(salva.getId());
        assertEquals("REC-999", salva.getCodigo());
    }

    @Test
    void deveEncontrarReceitaSalva() {
        ReceitaService service = new ReceitaService();

        Receita salva = service.salvar(new Receita(null, "REC-777", "Busca", "disperso"));

        assertTrue(service.buscarPorId(salva.getId()).isPresent());
    }
}
