package com.example.AccesoSegundoTrimestre.security.services;

import com.example.AccesoSegundoTrimestre.security.model.LoginRequest;
import com.example.AccesoSegundoTrimestre.security.model.RegisterRequest;
import com.example.AccesoSegundoTrimestre.security.model.AuthResponse;

/**
 * Interfaz para los servicios de autenticación.
 *
 * @author Alejandro Ramírez
 */
public interface AuthServiceI {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro.
     * @return La respuesta de autenticación.
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Inicia sesión en el sistema.
     *
     * @param request La solicitud de inicio de sesión.
     * @return La respuesta de autenticación.
     */
    AuthResponse login(LoginRequest request);

}
