package com.joaquin.curso.springboot.webapp.springbootweb.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.curso.springboot.webapp.springbootweb.models.User;
import com.joaquin.curso.springboot.webapp.springbootweb.models.dto.ParamDto;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {
   //Con los value se inyectan confiuguraciones
   @Value("${config.username}") //se estan inyectando se estan agregando los valosres declarados en el aplication.properties
    private String username;
    @Value("${config.message}")
    private String message;
    @Value("${config.listOfValues}")
    private String[] listOfValues;
    @Value("${config.code}")
    private Integer code;

    @Value("#{${config.valuesMap}}") //Se le puede pasar objetos enteros desde el config con el map siempre poner el #
    private Map<String,Object> valuesMap;

   @Value("#{${config.valuesMap}.product}") //Se le puede pasar como string tambien una variable
    private String product;

    @Value("#{${config.valuesMap}.price}") //Se le puede pasar como string tambien
    private Long price;

    @Autowired //busca un componente de spring que se encuentre almacenado en su contenedor
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message){ //Esto es como los requestParams pero aqui son obligatorios pasar los datos en el navegador se pone /y el mensaje
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

     @GetMapping("/mix/{product}/{id}")
     public Map<String, Object> mixPAthVar(@PathVariable String product, @PathVariable Long id){
        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
     }

     @PostMapping("/create") //Siempre que se hace un post es importante tener un constructor vacio en el objeto y que los datos enviados coincida 100% con el objeto y tener los métodos set
     public User create(@RequestBody User user){
        //Se hace algo con el usuario como un save en la base de datos
         user.setName(user.getName().toUpperCase());
        return user;
     }

     @GetMapping("/values")
     public Map<String,Object> values(){
      Map<String, Object> json= new HashMap<>();
      json.put("username", username);
      json.put("code", code);
      json.put("message", message);
      json.put("message2", environment.getProperty("config.message")); //Es otra manera de acceder a los datos
      json.put("code2", Integer.valueOf(environment.getProperty("config.code"))); //De forma normal siempre lo pasa como un string, hay que hacer siempre proceso de transformacion de tipo de dato
      json.put("listOfValues", listOfValues);
      json.put("valuesMap", valuesMap);
      json.put("product", product);
      json.put("price", price);
      return json;
     }
}
