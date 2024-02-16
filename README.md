# API Red Social: Documentación del Proyecto

Este proyecto es una API de red social desarrollada con Spring Boot. A continuación, se detalla la estructura del proyecto, explicando cada una de las clases y su funcionalidad.

## Configuración del Proyecto

El proyecto utiliza Maven para la gestión de dependencias y la construcción del mismo. La configuración de Maven se encuentra en el archivo `pom.xml`, donde se definen las dependencias del proyecto, incluyendo Spring Boot Starter, Lombok para la reducción de código boilerplate, y las dependencias necesarias para la seguridad, persistencia de datos y pruebas.

El archivo `application.properties` contiene la configuración de la base de datos, la configuración de Hibernate para la generación automática del esquema de base de datos (`spring.jpa.hibernate.ddl-auto=update`), y la configuración del JWT (JSON Web Token) para la autenticación de usuarios.

## Estructura del Proyecto

La estructura del proyecto sigue la convención de paquetes estándar de Spring Boot:

- **controllers**: Contiene los controladores REST que manejan las solicitudes HTTP entrantes y definen los puntos finales de la API.
- **dto**: Contiene los Data Transfer Objects (DTO) que se utilizan para transferir datos entre el cliente y el servidor.
- **model**: Contiene las entidades de datos que representan las tablas de la base de datos.
- **repositories**: Contiene las interfaces de los repositorios que se utilizan para interactuar con la base de datos.
- **security**: Contiene las clases relacionadas con la seguridad de la aplicación, como la autenticación y la autorización.
- **services**: Contiene las interfaces y las implementaciones de los servicios de la aplicación.

## Clases Principales

1. **UserController**: Controlador responsable de manejar las solicitudes relacionadas con los usuarios, como la obtención de detalles de usuario, la edición de descripciones y la gestión de seguidores.
2. **FollowController**: Controlador encargado de gestionar el seguimiento y el dejar de seguir usuarios.
3. **PublicationController**: Controlador que maneja las operaciones relacionadas con las publicaciones, como la creación, actualización y eliminación de publicaciones.
4. **CommentsController**: Controlador para gestionar los comentarios en las publicaciones.
5. **JWTAuthenticationFilter**: Filtro de autenticación JWT que intercepta las solicitudes entrantes y verifica la validez del token JWT.
6. **AuthServiceI**: Interfaz para los servicios de autenticación, que incluye métodos para el registro y el inicio de sesión de usuarios.
7. **AuthServiceImpl**: Implementación de los servicios de autenticación, que maneja el registro y el inicio de sesión de usuarios utilizando tokens JWT.
8. **JWTServiceI**: Interfaz para el servicio de gestión de tokens JWT, que define métodos para generar, validar y obtener reclamos de tokens JWT.
9. **JWTServiceImpl**: Implementación del servicio de gestión de tokens JWT, que proporciona métodos para generar, validar y obtener reclamos de tokens JWT.

## Configuración de Seguridad

- **SecurityConfig**: Configuración de seguridad de Spring que define las reglas de autorización y autenticación para los puntos finales de la API. También configura el filtro de autenticación JWT.

## Configuración Adicional

- **ApplicationConfig**: Configuración adicional de la aplicación, como la configuración del gestor de autenticación y del proveedor de autenticación personalizado.

# Configuración de la Base de Datos

El proyecto está configurado para utilizar MySQL como base de datos. La configuración de la base de datos se especifica en el archivo `application.properties`.

- ``spring.datasource.url=jdbc:mysql://localhost:3306/redsocial``
- ``spring.datasource.username=root``
- ``spring.datasource.password=root``
- ``spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver``
- ``spring.jpa.hibernate.ddl-auto=update``
- ``spring.jpa.show-sql=false``

# Configuración del JWT

 La configuración del JWT se realiza también en el archivo `application.properties`, donde se especifica el secreto utilizado para firmar los tokens JWT.

``jwt.secret=lMCvj7Sirkk41OpuXDBKoSA1YeQ4aTeHmP4gzoyoaLk=``

### Tecnologías Utilizadas
- ``Spring Boot``: Framework de desarrollo de aplicaciones Java.
- ``Spring Security``: Proporciona seguridad a las aplicaciones mediante la autenticación y autorización de usuarios.
- ``JWT (JSON Web Tokens)``: Método para autenticar usuarios en aplicaciones web.
- ``MySQL``: Sistema de gestión de bases de datos relacional utilizado para almacenar los datos de la aplicación.
- ``Lombok``: Biblioteca que simplifica la escritura de código eliminando la necesidad de escribir código repetitivo.

### Consideraciones Finales
- El proyecto está configurado para funcionar con Java 17.
- Se incluyen dependencias para Spring Boot, Spring Security, MySQL, Lombok, entre otros.
- Se utiliza Swagger para documentar y visualizar la API.
- Es importante mantener seguro el secreto JWT para evitar vulnerabilidades de seguridad.
- Se recomienda configurar adecuadamente las reglas de autorización en Spring Security para proteger las rutas de la aplicación.
- Se sugiere implementar pruebas unitarias y de integración para garantizar el correcto funcionamiento de la aplicación.

## Cómo Usar el Proyecto

### Clonar el Repositorio
git clone https://github.com/tu_usuario/tu_proyecto.git

### Configuración de la Base de Datos
Cambia los datos necesarios en el ``application.porperties`` y ajustalo a tus necesidades.

### Compilación y Ejecución
#### Compilación
mvn clean install

#### Ejecución
java -jar target/tu_proyecto.jar

### Uso de la API
1. Accede a la documentación de la API generada por Swagger para conocer las rutas disponibles y los parámetros necesarios para cada solicitud.
2. Realiza solicitudes HTTP a la API utilizando herramientas como Postman o cURL.

### Pruebas
1. Utiliza frameworks de pruebas como JUnit y Mockito para escribir y ejecutar pruebas unitarias e integración.

### Despliegue
1. Genera un archivo JAR ejecutable utilizando Maven o Gradle.
2. Despliega el archivo JAR en tu servidor de aplicaciones o en un servicio de alojamiento en la nube.

### Consideraciones de Seguridad
jwt.secret=lMCvj7Sirkk41OpuXDBKoSA1YeQ4aTeHmP4gzoyoaLk=
spring.jackson.serialization.write_dates_as_timestamps=false

### Mantenimiento
1. Realiza un seguimiento regular de las actualizaciones de las dependencias y del framework Spring Boot.
2. Realiza copias de seguridad de la base de datos y del código fuente de la aplicación de forma periódica.
