package com.example.AccesoSegundoTrimestre.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo para representar la solicitud de inicio de sesión.
 *
 * @author Alejandro Ramírez
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /** El nombre de usuario proporcionado en la solicitud de inicio de sesión. */
    String username;

    /** La contraseña proporcionada en la solicitud de inicio de sesión. */
    String password;
}

