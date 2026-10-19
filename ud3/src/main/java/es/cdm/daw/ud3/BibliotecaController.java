package es.cdm.daw.ud3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BibliotecaController {

    public record Libro (String titulo, String autor, int ano, int copias){}

    List <Libro> biblioteca = List.of(
            new Libro("Cien años de soledad", "Garcia Marquez", 1967, 3),
            new Libro("La sombra del viento", "Ruiz Zafon", 2001, 0),
            new Libro("El nombre de la rosa", "Umberto Eco", 1980, 2),
            new Libro("Don Quijote", "Cervantes", 1605, 4),
            new Libro("La casa de Bernarda Alba", "Garcia Lorca", 1936, 0),
            new Libro("El principito", "Saint-Exupery", 1943, 5)
    );

    @GetMapping("/catalogo")
    public String catalogo(
            @ModelAttribute("form") BibliotecaForm form,
            Model model) {

        String titulo = form.getTitulo();

        List<Libro> resultados = new ArrayList<>();

        for (Libro libro : biblioteca) {
            if (libro.titulo().toLowerCase()
                    .contains(titulo.toLowerCase())) {
                if (!form.isDisponibles() || libro.copias() > 0) {
                    resultados.add(libro);
                }
            }
        }

        model.addAttribute("biblioteca", resultados);

        return "catalogo";
    }
}
