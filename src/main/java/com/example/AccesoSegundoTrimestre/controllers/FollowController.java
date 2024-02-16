package com.example.AccesoSegundoTrimestre.controllers;

import com.example.AccesoSegundoTrimestre.services.FollowServiceI;
import com.example.AccesoSegundoTrimestre.services.UserServiceI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gestionar el seguimiento de usuarios.
 *
 * @author Alejandro Ramírez
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Follow", description = "Endpoints to manage user following")
public class FollowController {

    @Autowired
    private FollowServiceI followRepo;

    /**
     * Seguir a un usuario.
     *
     * @param followedUsername Nombre de usuario a seguir.
     * @param authentication Objeto Authentication para obtener el nombre del usuario que sigue.
     * @return ResponseEntity con el estado 200 (OK) si la operación se realizó con éxito.
     */
    @PostMapping("/follow/{followedUsername}")
    @Operation(summary = "Follow user")
    public ResponseEntity<Void> followedUser(@PathVariable String followedUsername, Authentication authentication) {
        String followerUsername = authentication.getName();

        followRepo.followUser(followerUsername, followedUsername);

        return ResponseEntity.ok().build();
    }

    /**
     * Dejar de seguir a un usuario.
     *
     * @param followedUsername Nombre de usuario a dejar de seguir.
     * @param authentication Objeto Authentication para obtener el nombre del usuario que deja de seguir.
     * @return ResponseEntity con el estado 200 (OK) si la operación se realizó con éxito.
     */
    @PostMapping("/unfollow/{followedUsername}")
    @Operation(summary = "Unfollow user")
    public ResponseEntity<Void> unfollowedUser(@PathVariable String followedUsername, Authentication authentication) {
        String followerUsername = authentication.getName();

        followRepo.unfollowUser(followerUsername, followedUsername);

        return ResponseEntity.ok().build();
    }

}
