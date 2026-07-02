package br.com.andre.dyelab.receita;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * PONTE JAX-RS -> Spring Web:
 *
 *   JAX-RS (legado)              Spring Web (aqui)
 *   @Path("/receitas")           @RequestMapping("/api/receitas")
 *   @GET                         @GetMapping
 *   @POST                        @PostMapping
 *   @PathParam("id")             @PathVariable
 *   @QueryParam("q")             @RequestParam
 *   @Produces(APPLICATION_JSON)  implícito — Jackson serializa por padrão
 *   Response                     ResponseEntity<T>
 *
 * INJEÇÃO POR CONSTRUTOR (repare: sem @Inject e sem @Autowired):
 * quando a classe tem UM construtor, o Spring injeta por ele automaticamente.
 * É a forma recomendada — o campo pode ser final (imutável) e a classe é
 * testável com "new" puro no JUnit, sem subir container. Guarde esse argumento:
 * é resposta pronta de entrevista sobre "field vs constructor injection".
 */
@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Receita> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)                 // 200 + corpo
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    @PostMapping
    public ResponseEntity<Receita> criar(@RequestBody Receita receita) {
        Receita salva = service.salvar(receita);
        // 201 Created + header Location — o mesmo cuidado que Response.created() no JAX-RS
        return ResponseEntity
                .created(URI.create("/api/receitas/" + salva.getId()))
                .body(salva);
    }
}
