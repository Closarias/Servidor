package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogoController {

    public record Articulo(String nombre, double precio, int stock) { }

    List<Articulo> catalogo = List.of(
            new Articulo("Cubierta 700x25", 24.90, 12),
            new Articulo("Camara 700", 5.50, 40),
            new Articulo("Cadena 11v", 34.00, 0)
    );

    @GetMapping("/catalogo")
    public String catalogo(Model modelo) {

        modelo.addAttribute("catalogo", catalogo);

        return "catalogo";
    }

}
