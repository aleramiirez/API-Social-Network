package com.example.AccesoSegundoTrimestre.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo para representar la solicitud de registro de usuario.
 *
 * @author Alejandro Ramírez
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /** El nombre de usuario proporcionado en la solicitud de registro. */
    String username;

    /** El correo electrónico proporcionado en la solicitud de registro. */
    String email;

    /** La descripción proporcionada en la solicitud de registro. */
    String description;

    /** La contraseña proporcionada en la solicitud de registro. */
    String password;

}
