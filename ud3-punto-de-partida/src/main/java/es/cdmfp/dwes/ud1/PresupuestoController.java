package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * El controlador con el que termina la UD2: lee parametros, calcula el desglose
 * con IVA y devuelve una plantilla. Hace SIEMPRE lo mismo.
 *
 * La UD3 empieza aqui: decidir, repetir y comprobar.
 */
@Controller
public class PresupuestoController {

    @GetMapping("/presupuesto")
    public String formulario() {
        return "presupuesto-formulario";
    }

    @PostMapping("/presupuesto")
    public String presupuesto(
            @ModelAttribute PresupuestoForm form,
            Model modelo) {

        String producto = form.getProducto();
        int unidades = form.getUnidades();
        double precio = form.getPrecio();
        boolean montaje = form.isMontaje();

        double base = precio * unidades;
        double iva = base * 0.21;
        double total = base + iva;

        if (montaje) {
            total += 15;
        }

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("unidades", unidades);
        modelo.addAttribute("precio", precio);
        modelo.addAttribute("base", base);
        modelo.addAttribute("iva", iva);
        modelo.addAttribute("total", total);

        return "presupuesto";
    }
}
