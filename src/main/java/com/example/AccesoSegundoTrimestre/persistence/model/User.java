package com.example.AccesoSegundoTrimestre.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un usuario en el sistema.
 *
 * @author Alejandro Ramírez
 */
@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    /** Identificador único del usuario */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    /** Nombre de usuario */
    @Column(name = "username", unique = true, nullable = false)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    /** Correo electrónico del usuario */
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "The email cannot be empty")
    private String email;

    /** Contraseña del usuario */
    @Column(name = "password", nullable = false)
    @NotBlank(message = "The password cannot be empty")
    @Size(min = 6, message = "The password must be at least 6 characters long")
    private String password;

    /** Descripción del usuario */
    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    /** Fecha de creación del usuario */
    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    /** Rol del usuario */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Constructor para la creación de usuarios.
     *
     * @param username    Nombre de usuario.
     * @param email       Correo electrónico del usuario.
     * @param description Descripción del usuario.
     * @param password    Contraseña del usuario.
     * @param role        Rol del usuario.
     */
    public User(String username, String email, String description, String password, Role role) {
        this.username = username;
        this.email = email;
        this.description = description;
        this.password = password;
        this.role = role;
    }

    // Métodos de UserDetails

    /**
     * Retorna la colección de autoridades asignadas al usuario.
     *
     * @return Colección de autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     *
     * @return true si la cuenta no ha expirado, false de lo contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada o no.
     *
     * @return true si la cuenta no está bloqueada, false de lo contrario.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado o no.
     *
     * @return true si las credenciales no han expirado, false de lo contrario.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado o no.
     *
     * @return true si el usuario está habilitado, false de lo contrario.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
