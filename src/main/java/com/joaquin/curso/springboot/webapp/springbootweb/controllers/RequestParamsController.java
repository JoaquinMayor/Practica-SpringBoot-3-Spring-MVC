package com.joaquin.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.curso.springboot.webapp.springbootweb.models.dto.ParamDto;
import com.joaquin.curso.springboot.webapp.springbootweb.models.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {
    
    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal") String message){ //la api se completa con la variable pasada por parametros en el navegador con el mismo nombre, despues del foo se pone ?message=mensaje
        
        ParamDto param = new ParamDto();
        param.setMessage(message);

        return param;
    }

    @GetMapping("/bar")
    public ParamMixDto bar(@RequestParam String text, @RequestParam Integer code){
        ParamMixDto params = new ParamMixDto();
        params.setMessage(text);
        params.setCode(code); // Los valores en el navegador se separan con &  
        return params;
    }

    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest request){ //Esto es lo mismo a lo de arriba pero se hace de manera nativa
        ParamMixDto params = new ParamMixDto();
        Integer code = 0;
        try{
            code = Integer.parseInt(request.getParameter("code"));
        }catch(Exception e){
            System.out.println(e);
        }
        
        params.setCode(code); //el request siempre devuelve un string
        params.setMessage(request.getParameter("text"));

        return params;
    }
}
