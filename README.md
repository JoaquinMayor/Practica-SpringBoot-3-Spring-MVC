# Manejo de Controladores (Request, Get y Post Mapping) en SpringBoot

### En el siguiente código encontraremos diferentes manerás y carcateristicas que pueden tener los controladores de SpringBoot asi como armar diferentes peticiones y algo de manejo de inyección de dependencia y el @Value.

#### *Se hará uso de DTO de la clase user para profundizar en estos conceptos *

#### Todos los controladores se encontrarán dentro de la carpeta  **controllers** con sus respectivos temas .

#### Recordar que la información de los @Value se emciemtran dentro de *values.properties*.

<p>
Dicho diseño se sin la carpeta y archivos service para comprender y no generar confuicipon en el manejo de los controladores.
</p>

![Querys Base de Datos](https://refactorizando.com/wp-content/uploads/2023/02/controller-spring.jpg)

---

## Objetivo

Proyecto de práctica del curso de Spring Boot 3 para aprender a construir **controladores web**: controladores MVC que devuelven vistas **Thymeleaf** y controladores REST que devuelven JSON, recibiendo datos por `@RequestParam`, `@PathVariable`, `HttpServletRequest` y `@RequestBody`, además de inyectar configuración externa con `@Value` y `Environment` desde un archivo `values.properties` propio.

## Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 3.2.1 |
| Spring Web (`spring-boot-starter-web`) | gestionada por Spring Boot |
| Thymeleaf (`spring-boot-starter-thymeleaf`) | gestionada por Spring Boot |
| Spring Boot DevTools | gestionada por Spring Boot |
| Maven (wrapper `mvnw`) | - |

No utiliza base de datos; los datos de ejemplo se crean en memoria dentro de los controladores.

## Estructura del proyecto

```
src/main/java/com/joaquin/curso/springboot/webapp/springbootweb
├── SpringbootWebApplication.java     # Clase principal
├── ValuesConfig.java                 # @PropertySource que carga values.properties (UTF-8)
├── controllers
│   ├── HomeController.java           # "/", "/home" -> forward a /details
│   ├── UserController.java           # @Controller con vistas Thymeleaf (details, list)
│   ├── UserRestController.java       # @RestController bajo /api
│   ├── RequestParamsController.java  # @RequestParam y HttpServletRequest bajo /api/params
│   └── PathVariableController.java   # @PathVariable, @RequestBody y @Value bajo /api/var
└── models
    ├── User.java                     # name, lastname, email
    └── dto
        ├── UserDto.java              # title + user
        ├── ParamDto.java             # message
        └── ParamMixDto.java          # message + code

src/main/resources
├── application.properties            # server.port=8090
├── values.properties                 # Valores inyectados con @Value (config.*)
└── templates
    ├── details.html                  # Detalle de un usuario + enlaces de ejemplo
    └── list.html                     # Tabla de usuarios con th:each / th:if
```

## Conceptos aplicados

- **`@Controller` vs `@RestController`**: el primero retorna el nombre de una plantilla (sin `.html`); el segundo serializa la respuesta a JSON.
- **`@RequestMapping`** a nivel de clase como ruta de primer nivel (`/api`, `/api/params`, `/api/var`) y **`@GetMapping` / `@PostMapping`** en los métodos.
- **`Model` / `ModelMap`** para pasar datos a la vista, y **`@ModelAttribute("users")`** para compartir un atributo con todos los métodos del controlador.
- **Thymeleaf**: `th:text`, `th:if`, `th:each`, y construcción de enlaces con `@{...}` (concatenando variables o usando la sintaxis `(param='valor')`).
- **`forward:` vs `redirect:`**: `HomeController` usa `forward:details`, que mantiene la misma petición HTTP y no cambia la URL (la alternativa `redirect:` queda comentada).
- **DTOs** (`UserDto`, `ParamDto`, `ParamMixDto`) para dar forma a las respuestas.
- **`@RequestParam`** con `required = false` y `defaultValue`, y varios parámetros separados por `&`.
- **`HttpServletRequest`** para leer parámetros de forma nativa (siempre llegan como `String`).
- **`@PathVariable`**: los valores son obligatorios y forman parte de la ruta.
- **`@RequestBody`** en un `POST`: el objeto necesita constructor vacío, setters y que el JSON coincida con sus atributos.
- **Inyección de configuración**:
  - `@PropertySource` en `ValuesConfig` para cargar `values.properties`.
  - `@Value("${...}")` para `String`, `Integer` y arreglos (`String[]` separado por comas).
  - Expresiones SpEL `@Value("#{${config.valuesMap}}")` para obtener un `Map` y `#{${config.valuesMap}.product}` para un valor puntual.
  - `Environment.getProperty(...)` como alternativa (devuelve `String`, requiere conversión de tipo).
- **Inyección de dependencias** con `@Autowired`.

## Endpoints

Puerto: **8090**.

### Vistas (Thymeleaf)

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/`, `/home` | Hace `forward` a `/details`. |
| GET | `/details` | Vista `details.html` con título y datos de un usuario, más enlaces de ejemplo a los endpoints de parámetros. |
| GET | `/list` | Vista `list.html` con la tabla de usuarios (provista por `@ModelAttribute("users")`). |

### API REST

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/details` | Devuelve un `UserDto` (título + usuario). |
| GET | `/api/list` | Devuelve una lista de 3 usuarios. |
| GET | `/api/detailsMap` | Igual que `/api/details` pero usando un `Map`. |
| GET | `/api/params/foo?message=...` | Devuelve el `message` recibido (opcional, por defecto "Hola qué tal"). |
| GET | `/api/params/bar?text=...&code=...` | Devuelve `text` y `code` en un `ParamMixDto`. |
| GET | `/api/params/request?code=...&text=...` | Igual que `bar` pero leyendo los parámetros desde `HttpServletRequest`. |
| GET | `/api/var/baz/{message}` | Devuelve el `message` recibido como path variable. |
| GET | `/api/var/mix/{product}/{id}` | Devuelve `product` e `id` recibidos en la ruta. |
| POST | `/api/var/create` | Recibe un `User` en JSON (`name`, `lastname`, `email`) y lo devuelve con el nombre en mayúsculas. |
| GET | `/api/var/values` | Muestra los valores inyectados desde `values.properties` con `@Value` y `Environment`. |

## Configuración

`src/main/resources/application.properties`:

```properties
server.port=8090
```

`src/main/resources/values.properties` contiene las claves `config.code`, `config.username`, `config.message`, `config.listOfValues` y `config.valuesMap` usadas en `PathVariableController`. No requiere base de datos.

## Cómo ejecutarlo

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Luego abre `http://localhost:8090/` en el navegador. Ejemplos con `curl`:

```bash
curl "http://localhost:8090/api/params/bar?text=Hola&code=10"
curl http://localhost:8090/api/var/mix/teclado/5
curl -X POST http://localhost:8090/api/var/create -H "Content-Type: application/json" -d '{"name":"joaquin","lastname":"mayor","email":"correo@ejemplo.com"}'
curl http://localhost:8090/api/var/values
```
