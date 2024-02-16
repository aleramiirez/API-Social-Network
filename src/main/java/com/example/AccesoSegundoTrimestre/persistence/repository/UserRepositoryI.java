package com.example.AccesoSegundoTrimestre.persistence.repository;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz que define métodos de acceso a datos para la entidad de usuario.
 *
 * @author Alejandro Ramírez
 */
@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Usuario encontrado, si existe.
     */
    Optional<User> findByUsername(String username);

}
