package es.cdm.daw.ud3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SocioController {

    public record Socio(String numero, int edad, String modalidad){}

    private final List<Socio> socios = new ArrayList<>();

    @GetMapping("/socio/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("form", new SocioForm());

        return "socio-formulario";
    }

    @PostMapping("/socio")
    public String alta(
          @ModelAttribute("form") SocioForm form, Model model
    ){
        List<String> errores = new ArrayList<>();

        if (form.getNombre() == null || form.getNombre().isBlank()) {
            errores.add("El nombre no puede estar vacío.");
        }

        if (form.getEmail() == null || !form.getEmail().contains("@")) {
            errores.add("El email debe contener una arroba.");
        }

        if (form.getEdad() < 5 || form.getEdad() > 120) {
            errores.add("La edad tiene que estar entre 5 y 120.");
        }

        if (!"general".equals(form.getModalidad())
                && !"reducida".equals(form.getModalidad())) {
            errores.add("La modalidad debe ser general o reducida.");
        }

        if (!errores.isEmpty()) {
            model.addAttribute("errores", errores);
            return "socio-formulario";
        }

        String nombre = form.getNombre().trim();
        String letras = nombre.substring(0, Math.min(3, nombre.length()));
        String numero = letras.toUpperCase() + Year.now().getValue();

        Socio socio = new Socio(numero, form.getEdad(), form.getModalidad());
        socios.add(socio);

        return "redirect:/socio/alta?numero=" + numero;
    }

    @GetMapping("/socio/alta")
    public String confirmacion(
            @RequestParam String numero,
            Model model) {

        for (Socio socio : socios) {
            if (socio.numero().equals(numero)) {
                model.addAttribute("numero", socio.numero());
                model.addAttribute("cuota",
                        calcularCuota(socio.edad(), socio.modalidad()));
                return "socio-alta";
            }
        }

        return "redirect:/socio/nuevo";
    }

    private int calcularCuota(int edad, String modalidad) {
        if (edad < 14 || edad > 65) {
            return 0;
        }

        if ("reducida".equals(modalidad)) {
            return 9;
        }

        return 18;
    }
}
