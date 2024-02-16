package com.example.AccesoSegundoTrimestre.security.services;

import com.example.AccesoSegundoTrimestre.persistence.model.Role;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import com.example.AccesoSegundoTrimestre.security.model.LoginRequest;
import com.example.AccesoSegundoTrimestre.security.model.RegisterRequest;
import com.example.AccesoSegundoTrimestre.security.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementación de los servicios de autenticación.
 *
 * @author Alejandro Ramírez
 */
@Service
public class AuthServiceImpl implements AuthServiceI {

    @Autowired
    private UserRepositoryI userRepo;

    @Autowired
    private JWTServiceI jwtMngm;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro.
     * @return La respuesta de autenticación.
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getDescription(),
                passwordEncoder.encode(request.getPassword()), Role.USER);
        userRepo.save(user);
        return new AuthResponse(jwtMngm.getToken(user));
    }

    /**
     * Inicia sesión en el sistema.
     *
     * @param request La solicitud de inicio de sesión.
     * @return La respuesta de autenticación.
     */
    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        User user = userRepo.findByUsername(request.getUsername()).orElseThrow();
        return new AuthResponse(jwtMngm.getToken(user));
    }
}
