package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.User;

import java.util.List;

/**
 * Interfaz que define los servicios relacionados con los usuarios.
 *
 * @author Alejandro Ramírez
 */
public interface UserServiceI {

    /**
     * Encuentra un usuario por su nombre de usuario y lo convierte en un objeto DTO.
     *
     * @param username Nombre de usuario del usuario a buscar.
     * @return DTO del usuario encontrado.
     */
    UserDto findUserByUsernameDTO(String username);

    /**
     * Actualiza la descripción de un usuario.
     *
     * @param username Nombre de usuario del usuario a actualizar.
     * @param userUpdateDTO DTO del usuario con la nueva descripción.
     * @return DTO del usuario actualizado.
     */
    UserDto updateUserDescription(String username, UserDto userUpdateDTO);

    /**
     * Obtiene una lista de DTOs de los seguidores de un usuario.
     *
     * @param username Nombre de usuario del usuario del cual se obtienen los seguidores.
     * @return Lista de DTOs de los seguidores del usuario.
     */
    List<UserDto> getFollowers(String username);

    /**
     * Obtiene una lista de DTOs de los usuarios seguidos por un usuario.
     *
     * @param username Nombre de usuario del usuario del cual se obtienen los seguidos.
     * @return Lista de DTOs de los usuarios seguidos por el usuario.
     */
    List<UserDto> getFollows(String username);

}
