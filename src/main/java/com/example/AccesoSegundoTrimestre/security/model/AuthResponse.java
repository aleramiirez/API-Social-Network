package com.example.AccesoSegundoTrimestre.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Clase modelo para representar la respuesta de autenticación que contiene un token.
 *
 * @author Alejandro Ramírez
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    /** El token de autenticación generado. */
    String token;

}
