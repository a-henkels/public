package br.com.andre.dyelab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicação.
 *
 * PONTE Java EE -> Spring:
 * No WildFly, o "main" é o servidor de aplicação: você empacota um .war e o
 * container (Undertow + Weld + ...) sobe sua aplicação. Aqui é o inverso —
 * a aplicação É o main, e ela embute o servidor (Tomcat, por padrão).
 * Não existe deploy: você roda um jar.
 *
 * @SpringBootApplication combina três coisas:
 *  - @Configuration: esta classe pode declarar beans (análogo a uma classe produtora CDI)
 *  - @EnableAutoConfiguration: o Spring configura o que encontra no classpath
 *    (análogo ao que o WildFly faz por subsistemas, mas dirigido por dependências do pom)
 *  - @ComponentScan: escaneia este pacote e subpacotes atrás de beans
 *    (análogo ao bean discovery do Weld, mas SEM beans.xml — o default é
 *    "annotated": só vira bean quem tem estereótipo explícito)
 */
@SpringBootApplication
public class DyeLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyeLabApplication.class, args);
    }
}
