# Proyecto Franquicias - Prueba Técnica

Este proyecto es una aplicación básica desarrollada en Spring Boot para gestionar franquicias, sucursales y productos.

## Estructura del Proyecto

La estructura de carpetas del proyecto es la siguiente:

- **src/**: Código fuente del proyecto.
  - **main/**: Código principal de la aplicación.
    - **java/**: Código Java de la aplicación.
      - **com/example/franquicias/controllers/**: Controladores que manejan las solicitudes HTTP.
      - **com/example/franquicias/model/**: Clases que representan las entidades del dominio.
      - **com/example/franquicias/repository/**: Interfaces para la interacción con la base de datos.
      - **com/example/franquicias/service/**: Lógica de negocio de la aplicación.
    - **resources/**: Archivos de configuración y datos.
      - **application.properties**: Configuración de la aplicación.
- **.gitignore**: Especifica los archivos y directorios que Git debe ignorar.
- **docker-compose.yml**: Define y configura los servicios de Docker para la aplicación.
- **Dockerfile**: Dockerfile para la configuración del contenedor de la aplicación.
- **mvnw** y **mvnw.cmd**: Scripts para ejecutar Maven Wrapper en Unix y Windows, respectivamente.
- **pom.xml**: Archivo de configuración de Maven que gestiona las dependencias y plugins del proyecto.

## Tecnologías Utilizadas

El proyecto utiliza las siguientes tecnologías:

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.4.4**: Framework para el desarrollo de aplicaciones Java.
- **MySQL**: Sistema de gestión de bases de datos relacional.
- **Docker**: Plataforma para desarrollar, enviar y ejecutar aplicaciones en contenedores.
- **Docker Compose**: Herramienta para definir y gestionar aplicaciones Docker multi-contenedor.
- **Maven**: Herramienta de gestión y construcción de proyectos Java.

## Ejecución del Proyecto con Docker Compose

Para ejecutar el proyecto utilizando Docker Compose, sigue estos pasos:

1. **Clonar el repositorio**:

```bash
   git clone https://github.com/AAlbaB/franquicias.git
```

2. **Navegar al directorio del proyecto**:

```bash
   cd franquicias
```

3. **Construir y ejecutar los contenedores**:

```bash
   docker-compose up --build -d
```

## Acceder a la aplicación

Una vez que los contenedores estén en funcionamiento, la aplicación estará disponible en `http://localhost:9191`

Se pueden verificar el funcionamiento con la colección adjunta `collections/coleccionFranquicias.postman_collection.json`

## Detener la aplicación
Para detener y eliminar los contenedores en ejecución, puedes utilizar el siguiente comando (Quitar -v para dejar los volúmenes creados):

```bash
   docker-compose down -v
```