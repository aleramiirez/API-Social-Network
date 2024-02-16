package com.example.AccesoSegundoTrimestre.security.config;

import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de la aplicación.
 *
 * @author Alejandro Ramírez
 */
@Configuration
public class ApplicationConfig {

    @Autowired
    private UserRepositoryI userRepo;

    /**
     * Bean para obtener el gestor de autenticación.
     *
     * @param config Configuración de autenticación.
     * @return Gestor de autenticación.
     * @throws Exception Si hay un error al obtener el gestor de autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean para proporcionar un proveedor de autenticación personalizado.
     *
     * @return Proveedor de autenticación.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean para proporcionar un servicio de detalles de usuario personalizado.
     *
     * @return Servicio de detalles de usuario.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Bean para proporcionar un codificador de contraseñas.
     *
     * @return Codificador de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
