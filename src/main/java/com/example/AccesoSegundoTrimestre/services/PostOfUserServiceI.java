package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.PostOfUserDto;

import java.util.List;

/**
 * Interfaz para el servicio que gestiona las publicaciones de los usuarios.
 *
 * @author Alejandro Ramírez
 */
public interface PostOfUserServiceI {

    /**
     * Obtiene todas las publicaciones de todos los usuarios.
     *
     * @return Lista de todas las publicaciones de todos los usuarios.
     */
    List<PostOfUserDto> getAllPublications();

    /**
     * Obtiene las publicaciones de un usuario dado su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Publicaciones del usuario.
     */
    PostOfUserDto getPublicationByUsername(String username);

    /**
     * Obtiene las publicaciones de los usuarios seguidos por un usuario dado su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Publicaciones de los usuarios seguidos por el usuario.
     */
    List<PostOfUserDto> getPublicationsOfFollowedUser(String username);

}
