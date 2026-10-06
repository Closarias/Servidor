package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PresupuestoController {

    @GetMapping("/presupuesto")
    public String presupuesto(@RequestParam String cliente,
                              @RequestParam int unidades,
                              @RequestParam double precio,
                              Model modelo) {

        double base  = precio * unidades;
        double unitario = base / unidades;

        if (cliente.equals("club")) {
            base = base * 0.9;
        }

        modelo.addAttribute("base", base);
        modelo.addAttribute("unitario", unitario);
        return "presupuesto";
    }

}
