package com.example.AccesoSegundoTrimestre.security.controllers;

import com.example.AccesoSegundoTrimestre.security.model.AuthResponse;
import com.example.AccesoSegundoTrimestre.security.model.LoginRequest;
import com.example.AccesoSegundoTrimestre.security.model.RegisterRequest;
import com.example.AccesoSegundoTrimestre.security.services.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que gestiona las operaciones de autenticación, como el registro y el inicio de sesión de usuarios.
 *
 * @author Alejandro Ramírez
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
@Order(1)
public class AuthController {

    @Autowired
    private AuthServiceImpl authMngm;

    /**
     * Endpoint para el registro de usuarios.
     *
     * @param request Datos de registro del usuario.
     * @return Respuesta con el token de autenticación.
     */
    @PostMapping(value = "/register")
    @Operation(summary = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authMngm.register(request));
    }

    /**
     * Endpoint para la autenticación de usuarios.
     *
     * @param request Datos de inicio de sesión del usuario.
     * @return Respuesta con el token de autenticación.
     */
    @PostMapping(value = "/login")
    @Operation(summary = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authMngm.login(request));
    }

}
