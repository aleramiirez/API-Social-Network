package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.persistence.model.Follow;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.FollowRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para el seguimiento de usuarios.
 *
 * @author Alejandro Ramírez
 */
@Service
public class FollowServiceImpl implements FollowServiceI {

    private final FollowRepositoryI followRepo;
    private final UserRepositoryI userRepo;

    /**
     * Constructor de FollowServiceImpl.
     *
     * @param followRepo Repositorio de seguimientos.
     * @param userRepo   Repositorio de usuarios.
     */
    @Autowired
    public FollowServiceImpl(FollowRepositoryI followRepo, UserRepositoryI userRepo) {
        this.followRepo = followRepo;
        this.userRepo = userRepo;
    }

    /**
     * Método para seguir a un usuario.
     *
     * @param followerUsername Nombre de usuario del seguidor.
     * @param followedUsername Nombre de usuario del usuario seguido.
     * @throws IllegalArgumentException Si se intenta seguir al mismo usuario.
     */
    @Override
    public void followUser(String followerUsername, String followedUsername) {
        if (followerUsername.equals(followedUsername)) {
            throw new IllegalArgumentException("You can't follow yourself");
        }

        User follower = getUserByUsername(followerUsername);
        User followed = getUserByUsername(followedUsername);

        if (!isFollowed(follower, followed)) {
            Follow follow = new Follow();
            follow.setFollower(follower);
            follow.setFollowed(followed);
            followRepo.save(follow);
        }
    }

    /**
     * Método para dejar de seguir a un usuario.
     *
     * @param followerUsername Nombre de usuario del seguidor.
     * @param followedUsername Nombre de usuario del usuario seguido.
     */
    @Override
    public void unfollowUser(String followerUsername, String followedUsername) {
        User follower = getUserByUsername(followerUsername);
        User followed = getUserByUsername(followedUsername);

        followRepo.deleteByFollowerUsernameAndFollowedUsername(followerUsername, followedUsername);
    }

    /**
     * Verifica si un usuario está siendo seguido por otro.
     *
     * @param follower Usuario seguidor.
     * @param followed Usuario seguido.
     * @return true si el usuario está siendo seguido, false de lo contrario.
     */
    private boolean isFollowed(User follower, User followed) {
        return followRepo.existsByFollowerAndFollowed(follower, followed);
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return El usuario encontrado.
     * @throws UsernameNotFoundException Si no se encuentra al usuario.
     */
    private User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
