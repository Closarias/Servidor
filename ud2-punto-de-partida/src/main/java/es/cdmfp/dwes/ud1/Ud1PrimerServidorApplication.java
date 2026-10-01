package es.cdmfp.dwes.ud1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion.
 *
 * @SpringBootApplication hace tres cosas a la vez:
 *   - marca esta clase como clase de configuracion,
 *   - activa la autoconfiguracion (es la que arranca Tomcat y monta Thymeleaf),
 *   - y busca componentes (@Controller, @Service...) en este paquete y en los de debajo.
 *
 * Por eso los controladores tienen que estar en es.cdmfp.dwes.ud1 o mas abajo.
 */
@SpringBootApplication
public class Ud1PrimerServidorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ud1PrimerServidorApplication.class, args);
    }
}

