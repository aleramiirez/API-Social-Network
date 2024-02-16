package com.example.AccesoSegundoTrimestre.controllers;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.services.PublicationServiceI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar las publicaciones.
 *
 * @author Alejandro Ramírez
 */
@RestController
@RequestMapping("/publications")
@CrossOrigin
@Tag(name = "Publications", description = "Operations related to Publications")
public class PublicationController {

    @Autowired
    private PublicationServiceI publicationMgmt;

    /**
     * Crear una nueva publicación.
     *
     * @param publicationRequest Objeto PublicationDto que contiene la información de la nueva publicación.
     * @param authentication Objeto Authentication para obtener el nombre de usuario del usuario autenticado.
     * @return ResponseEntity con el objeto PublicationDto creado.
     */
    @PostMapping("/create")
    @Operation(summary = "Create Publication")
    public ResponseEntity<PublicationDto> createPublication(@RequestBody PublicationDto publicationRequest,
                                                            Authentication authentication) {
        String username = authentication.getName();
        PublicationDto createdPublication = publicationMgmt.addPublication(username, publicationRequest.getText());
        return ResponseEntity.ok(createdPublication);
    }

    /**
     * Actualizar una publicación existente.
     *
     * @param publicationID ID de la publicación a actualizar.
     * @param publicationRequest Objeto PublicationDto con la nueva información de la publicación.
     * @param authentication Objeto Authentication para obtener el nombre de usuario del usuario autenticado.
     * @return ResponseEntity con el objeto PublicationDto actualizado.
     */
    @PutMapping("update/{publicationID}")
    @Operation(summary = "Update Publication")
    public ResponseEntity<PublicationDto> updatePost(@PathVariable Long publicationID,
                                                     @RequestBody PublicationDto publicationRequest,
                                                     Authentication authentication) {

        String username = authentication.getName();
        PublicationDto updatePublication = publicationMgmt.updatePublication(publicationID, username,
                publicationRequest.getText());

        return ResponseEntity.ok(updatePublication);
    }

    /**
     * Eliminar una publicación existente.
     *
     * @param publicationID ID de la publicación a eliminar.
     * @param authentication Objeto Authentication para obtener el nombre de usuario del usuario autenticado.
     * @return ResponseEntity con un mensaje indicando que la publicación ha sido eliminada con éxito.
     */
    @DeleteMapping("delete/{publicationID}")
    @Operation(summary = "Delete Publication")
    public ResponseEntity<String> deletePost(@PathVariable Long publicationID, Authentication authentication) {

        String username = authentication.getName();
        publicationMgmt.deletePublication(publicationID, username);

        return ResponseEntity.ok("Publication successfully deleted");
    }
}
