package es.cdmfp.dwes.ud1;

import java.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * El primer controlador del curso.
 *
 * Un controlador no devuelve HTML: devuelve el NOMBRE de una plantilla.
 * Quien busca el fichero y lo rellena es Thymeleaf, y lo hace en el servidor.
 */
@Controller
public class SaludoController {

    @GetMapping("/saludo")
    public String saludo(Model modelo) {
        modelo.addAttribute("nombre", "2.º DAW");
        modelo.addAttribute("curso", "26/27");
        modelo.addAttribute("hora", LocalTime.now().withNano(0));
        return "saludo";              // busca src/main/resources/templates/saludo.html
    }

    /**
     * Segunda ruta, la de la actividad D1.
     * Lo que venga en la URL detras de /saludo/ entra en el parametro `nombre`.
     */
    @GetMapping("/saludo/{nombre}")
    public String saludoA(@PathVariable String nombre, Model modelo) {
        modelo.addAttribute("nombre", nombre);
        modelo.addAttribute("curso", "26/27");
        modelo.addAttribute("hora", LocalTime.now().withNano(0));
        return "saludo";
    }
}
