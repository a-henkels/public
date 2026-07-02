package br.com.andre.dyelab.receita;

/**
 * Receita de tingimento — a "lógica de processo" pura, independente de máquina
 * (exatamente a separação receita vs. perfil de máquina que você desenhou via ISA-88).
 *
 * SEMANA 1: classe simples, em memória. Sem JPA ainda — o foco é DI e web.
 *
 * SEMANA 2: transforme em entidade:
 *   - @Entity, @Table(name = "RECEITA")
 *   - @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) — você já conhece
 *     isso do Hibernate no sistema legado; as anotações são as MESMAS (jakarta.persistence).
 *     A diferença não está na entidade, está em quem gerencia o EntityManager.
 */
public class Receita {

    private Long id;
    private String codigo;
    private String descricao;
    private String classeProcesso; // ex.: tingimento reativo, disperso...

    public Receita() {
    }

    public Receita(Long id, String codigo, String descricao, String classeProcesso) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.classeProcesso = classeProcesso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getClasseProcesso() { return classeProcesso; }
    public void setClasseProcesso(String classeProcesso) { this.classeProcesso = classeProcesso; }
}
