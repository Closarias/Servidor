package es.cdmfp.dwes.ud1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class DemoErroresController {
    @GetMapping("/demo")
    @ResponseBody
    public String demo(@RequestParam int cantidad){
        return "Hemos recibido: "+cantidad;
    }
}
