package com.example.AccesoSegundoTrimestre.services;

/**
 * Interfaz para el servicio de seguimiento de usuarios.
 *
 * @author Alejandro Ramírez
 */
public interface FollowServiceI {

    /**
     * Método para seguir a un usuario.
     *
     * @param followerUsername Nombre de usuario del seguidor.
     * @param followedUsername Nombre de usuario del usuario seguido.
     */
    void followUser(String followerUsername, String followedUsername);

    /**
     * Método para dejar de seguir a un usuario.
     *
     * @param followerUsername Nombre de usuario del seguidor.
     * @param followedUsername Nombre de usuario del usuario seguido.
     */
    void unfollowUser(String followerUsername, String followedUsername);
}
