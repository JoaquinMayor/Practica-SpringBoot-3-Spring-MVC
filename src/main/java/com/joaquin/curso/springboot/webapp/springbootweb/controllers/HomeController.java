package com.joaquin.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping({"", "/","/home"})
    public String home(){ //Redirige cuando se pone en la barra de navegación hacia lo que pongamos en el return

        //return"redirect:/details";
        return "forward:details"; // El forward la diferencia es que se mantiene dentro de la petición http
        //y no se pierden los parámetros dentro del request, tampoco cambia la ruta url,
        //ya que despacha a otra acción del controlador pero sin recargar la página
    }
}
