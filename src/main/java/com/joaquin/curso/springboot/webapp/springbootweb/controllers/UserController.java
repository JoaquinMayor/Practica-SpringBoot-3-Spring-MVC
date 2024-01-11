package com.joaquin.curso.springboot.webapp.springbootweb.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.joaquin.curso.springboot.webapp.springbootweb.models.User;



@Controller //Indica que es un controllador de spring
public class UserController { //Los controladores reciben una petición y devuelben una respuesta(un html, un apirest, una vista, etc)

    //Con esto se le dice que maneja una petición del request, tienen distintos métodos los request, método get, método post, put o delete
    //Los métodos post son para cuando enviamos datos en un formulario por ejemplo
    //El put es para editar una información existente
    @GetMapping("/details")//Para cualquier petición que es una ruta url o un enlace
    public String details(Model model){
        User user = new User("Joaquín", "Mayor");
        user.setEmail("joaquin88mayor@gmail.com");
        model.addAttribute("title", "Hola Mundo Spring Boot"); //Con el model pasamos el valor, primero le ponemos un nombre y luego el valor
        model.addAttribute("user", user);

        return "details"; //El método retorna a la plantilla (dirección del html) con la cual va a trabajar, no se pone el .html, solo el nombre
    }

    @GetMapping("/list")
    public String list(ModelMap model){

        model.addAttribute("title", "Listado de usuarios");
        //model.addAttribute("users", users);

        return "list";
    }

    @ModelAttribute("users") //Se usa para pasarlo en todos los métodos del controlador, se encuentra tanto en list como en details, se usa para reutilizar los datos, lo que se encuentra dentro del model es el nombre del dato a ingresar en el html
    public List<User> usersModel(){
        List<User> users = Arrays.asList(new User("Joaquín", "Mayor", "joaquin88mayor@gmail.com"), 
            new User("Darío", "Mayor","dario98mayor@gmail.com"), 
            new User("Rosa", "Otero"));
        
            return users;
    }
}
