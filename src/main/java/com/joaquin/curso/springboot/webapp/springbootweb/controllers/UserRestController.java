package com.joaquin.curso.springboot.webapp.springbootweb.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.curso.springboot.webapp.springbootweb.models.User;
import com.joaquin.curso.springboot.webapp.springbootweb.models.dto.UserDto;

import org.springframework.web.bind.annotation.RequestMapping;





@RestController //Con esto lo convierte en un metodo andler para aser apis, lo devuelve como un json
@RequestMapping("/api")//sirve por si queremos usar metodos o direcciones con el mismo nombre, una ruta de primer nivel
public class UserRestController {


    @GetMapping("/details")//Para cualquier petición que es una ruta url o un enlace
    public UserDto details(){
        
        UserDto userDto = new UserDto();
        User user =new User("Joaquín", "Mayor");
        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");
        
        return userDto; //El metodo retorna a la plantilla (dirección del html) con la cual va a trabajar, no se pone el .html, solo el nombre
    }

    @GetMapping("/list")    
    public List<User> list(){
        User user =new User("Joaquín", "Mayor");
        User user2 =new User("Darío", "Mayor");
        User user3 =new User("Rosa", "Otero");
        
        List<User> users = new ArrayList<>();
        users.add(user3);
        users.add(user2);
        users.add(user);

        return users;
    }


    @GetMapping("/detailsMap")//Para cualquier petición que es una ruta url o un enlace
    public Map<String,Object> detailsMap(){
        User user =new User("Joaquín", "Mayor");
        Map<String,Object> json = new HashMap<>();

        json.put("title", "Hola Mundo Spring Boot"); //Con el model pasamos el valor, primero le ponemos un nombre y luego el valor
        json.put("user", user);

        return json; //El metodo retorna a la plantilla (dirección del html) con la cual va a trabajar, no se pone el .html, solo el nombre
    }
}
