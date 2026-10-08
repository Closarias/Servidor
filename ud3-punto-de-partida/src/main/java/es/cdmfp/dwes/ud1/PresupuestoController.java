package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String presupuesto(@RequestParam(defaultValue = "sin nombre") String producto,
                              @RequestParam(defaultValue = "1") int unidades,
                              @RequestParam(defaultValue = "0") double precio,
                              Model modelo) {

        double base = precio * unidades;
        double iva  = base * 0.21;

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("unidades", unidades);
        modelo.addAttribute("precio", precio);
        modelo.addAttribute("base", base);
        modelo.addAttribute("iva", iva);
        modelo.addAttribute("total", base + iva);

        return "presupuesto";
    }
}
