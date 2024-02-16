package com.example.AccesoSegundoTrimestre.controllers;


import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.services.UserServiceI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar los usuarios.
 *
 * @author Alejandro Ramírez
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Users", description = "User-related operations")
public class UserController {

    @Autowired
    private UserServiceI userMgmt;

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario a buscar.
     * @return ResponseEntity con el objeto UserDto del usuario encontrado.
     */
    @GetMapping("/public/{username}")
    @Operation(summary = "Get user by username")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        UserDto userDto = userMgmt.findUserByUsernameDTO(username);
        return ResponseEntity.ok(userDto);

    }

    /**
     * Edita la descripción de un usuario.
     *
     * @param userUpdateDTO Objeto UserDto con la nueva descripción del usuario.
     * @param authentication Objeto Authentication para obtener el nombre de usuario del usuario autenticado.
     * @return ResponseEntity con el objeto UserDto actualizado.
     */
    @PutMapping("/description")
    @Operation(summary = "Edit user description")
    public ResponseEntity<UserDto> updateDescription(@RequestBody UserDto userUpdateDTO, Authentication authentication) {
        String username = authentication.getName();
        UserDto updatedUser = userMgmt.updateUserDescription(username, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Obtiene los seguidores de un usuario.
     *
     * @param username Nombre de usuario del usuario del cual se desean obtener los seguidores.
     * @return ResponseEntity con una lista de UserDto representando los seguidores del usuario.
     */
    @GetMapping("/followers/{username}")
    @Operation(summary = "Get a user's followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String username) {
        List<UserDto> followers = userMgmt.getFollowers(username);
        return ResponseEntity.ok(followers);
    }

    /**
     * Obtiene los usuarios seguidos por un usuario.
     *
     * @param username Nombre de usuario del usuario del cual se desean obtener los seguidos.
     * @return ResponseEntity con una lista de UserDto representando los usuarios seguidos por el usuario.
     */
    @GetMapping("/follows/{username}")
    @Operation(summary = "Get a user's followeds")
    public ResponseEntity<List<UserDto>> getFollows(@PathVariable String username) {
        List<UserDto> followers = userMgmt.getFollows(username);
        return ResponseEntity.ok(followers);
    }


}
