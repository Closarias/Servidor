package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BicicletasController {

    @GetMapping({"/", "/bicicletas"})
    public String mostrarFormulario(Model model) {
        model.addAttribute("form", new BicicletaForm());
        return "bicicleta-form";
    }

    @PostMapping("/bicicleta")
    public String procesar(
            @ModelAttribute("form") BicicletaForm form,
            Model model) {

        List<String> errores = new ArrayList<>();

        int unidades = form.getUnidades();
        double precio = form.getPrecio();
        String tipo = form.getTipo();

        if (unidades < 1 || unidades > 100) {
            errores.add("Las unidades deben estar entre 1 y 100.");
        }

        if (precio <= 0) {
            errores.add("El precio debe ser mayor que 0.");
        }

        if (tipo == null || tipo.isBlank()) {
            errores.add("Debes elegir un tipo de bicicleta.");
        }

        if (!errores.isEmpty()) {
            model.addAttribute("errores", errores);
            return "bicicleta-form";
        }

        double base = precio * unidades;
        double iva = base * 0.21;
        double total = base + iva;

        model.addAttribute("unidades", unidades);
        model.addAttribute("precio", precio);
        model.addAttribute("tipo", tipo);
        model.addAttribute("montaje", form.isMontaje());
        model.addAttribute("base", base);
        model.addAttribute("iva", iva);
        model.addAttribute("total", total);

        return "bicicleta-form";
    }
}