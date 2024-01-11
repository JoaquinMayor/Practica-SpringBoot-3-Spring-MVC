package com.joaquin.curso.springboot.webapp.springbootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:values.properties", encoding = "UTF-8") //Para que reconozca y poder trabajar con nuestro properties creado
})
public class ValuesConfig {
    
}
