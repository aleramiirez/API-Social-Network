package com.example.AccesoSegundoTrimestre.controllers;

import com.example.AccesoSegundoTrimestre.dto.PostOfUserDto;
import com.example.AccesoSegundoTrimestre.services.PostOfUserServiceI;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para gestionar las publicaciones de los usuarios.
 *
 * @author Alejandro Ramírez
 */
@RestController
@RequestMapping("/users")
public class PostOfUserController {

    private final PostOfUserServiceI postOfUserMngm;

    /**
     * Constructor de la clase PostOfUserController.
     *
     * @param postOfUserMngm Servicio para gestionar las publicaciones de los usuarios.
     */
    @Autowired
    public PostOfUserController(PostOfUserServiceI postOfUserMngm) {
        this.postOfUserMngm = postOfUserMngm;
    }

    /**
     * Obtener las publicaciones de un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario del cual se obtendrán las publicaciones.
     * @return ResponseEntity con el objeto PostOfUserDto que contiene las publicaciones del usuario.
     */
    @GetMapping("/public/publications/{username}")
    @Operation(summary = "Get posts by username")
    public ResponseEntity<PostOfUserDto> getUserAndPublications(@PathVariable String username) {
        PostOfUserDto postOfUserDto = postOfUserMngm.getPublicationByUsername(username);

        return ResponseEntity.ok(postOfUserDto);
    }

    /**
     * Obtener las publicaciones de los usuarios seguidos por el usuario autenticado.
     *
     * @param authentication Objeto Authentication para obtener el nombre de usuario del usuario autenticado.
     * @return ResponseEntity con la lista de PostOfUserDto que contienen las publicaciones de los usuarios seguidos.
     */
    @GetMapping("/publications/followed")
    public ResponseEntity<List<PostOfUserDto>> getPublicationsOfFollowedUsers(Authentication authentication) {
        String username = authentication.getName();

        List<PostOfUserDto> postOfUserDtos = postOfUserMngm.getPublicationsOfFollowedUser(username);

        return ResponseEntity.ok(postOfUserDtos);
    }

    /**
     * Obtener todas las publicaciones de todos los usuarios.
     *
     * @return ResponseEntity con la lista de PostOfUserDto que contienen todas las publicaciones de todos los usuarios.
     */
    @GetMapping("/publications")
    public ResponseEntity<List<PostOfUserDto>> getAllPublications() {
        List<PostOfUserDto> postOfUserDtos = postOfUserMngm.getAllPublications();

        return ResponseEntity.ok(postOfUserDtos);
    }

}
