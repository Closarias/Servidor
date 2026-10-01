package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculadoraController {

    @GetMapping("/entrada")
    public String entrada(@RequestParam String nombre, @RequestParam(defaultValue = "0") double precio, @RequestParam(defaultValue = "1") Integer cantidad, Model model){

        double base = precio * cantidad;
        double iva = base * 0.21;
        double total = base + iva;

        model.addAttribute("nombre", nombre);
        model.addAttribute("precio", precio);
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("base", base);
        model.addAttribute("iva", iva);
        model.addAttribute("total", total);

        return "entrada";
    }

    @GetMapping("/prueba")
    public String prueba(Model model){
        return entrada("Ana", 3, 1, model);
    }
}
