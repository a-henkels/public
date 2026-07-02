package br.com.andre.dyelab.receita;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * PONTE CDI -> Spring (o coração da Semana 1):
 *
 * No legado você escreveria:            Aqui:
 *   @ApplicationScoped                    @Service
 *   public class ReceitaService {...}     public class ReceitaService {...}
 *
 *   @Inject ReceitaService service;       construtor (veja o Controller)
 *
 * Equivalências de escopo:
 *   @ApplicationScoped  -> singleton (DEFAULT do Spring — diferença importante!
 *                          no CDI o default é @Dependent; no Spring, singleton)
 *   @RequestScoped      -> @Scope("request")
 *   @Dependent          -> @Scope("prototype") (aproximação)
 *
 * @Service, @Repository e @Component são tecnicamente o mesmo bean —
 * são estereótipos semânticos (como usar qualifiers CDI pra documentar papel).
 * @Repository adiciona tradução de exceções de persistência (semana 2).
 *
 * SEMANA 1: repositório em memória de propósito. Na SEMANA 2 esta classe
 * passa a delegar a um ReceitaRepository (Spring Data JPA) e este Map morre.
 */
@Service
public class ReceitaService {

    private final Map<Long, Receita> emMemoria = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    public ReceitaService() {
        // carga inicial só pra API ter o que responder na semana 1
        salvar(new Receita(null, "REC-001", "Tingimento reativo algodão claro", "reativo"));
        salvar(new Receita(null, "REC-002", "Tingimento disperso poliéster", "disperso"));
    }

    public List<Receita> listarTodas() {
        return List.copyOf(emMemoria.values());
    }

    public Optional<Receita> buscarPorId(Long id) {
        return Optional.ofNullable(emMemoria.get(id));
    }

    public Receita salvar(Receita receita) {
        if (receita.getId() == null) {
            receita.setId(sequencia.getAndIncrement());
        }
        emMemoria.put(receita.getId(), receita);
        return receita;
    }
}
