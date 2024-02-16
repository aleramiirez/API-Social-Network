package com.example.AccesoSegundoTrimestre.controllers.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Manejador de excepciones personalizado para controladores.
 *
 * @author Alejandro Ramírez
 */
@ControllerAdvice
public class CustomExeptionHandler {

    /**
     * Maneja la excepción UsernameNotFoundException y devuelve una respuesta HTTP con un mensaje de error
     * y estado 404 (NOT FOUND).
     *
     * @param ex Excepción UsernameNotFoundException lanzada.
     * @return ResponseEntity con el mensaje de error y estado 404.
     */
    public ResponseEntity<String> handleUserNotFound(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
