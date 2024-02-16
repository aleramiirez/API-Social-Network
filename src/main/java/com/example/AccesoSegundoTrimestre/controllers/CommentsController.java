package com.example.AccesoSegundoTrimestre.controllers;

import com.example.AccesoSegundoTrimestre.dto.CommentsDto;
import com.example.AccesoSegundoTrimestre.services.CommentsServiceI;
import com.example.AccesoSegundoTrimestre.services.CommentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador para manejar las operaciones relacionadas con los comentarios en las publicaciones.
 *
 * @author Alejandro Ramírez
 */
@RestController
@RequestMapping("users/comments")
public class CommentsController {

    private final CommentsServiceI commentsMngm;

    /**
     * Constructor del controlador de comentarios.
     * @param commentsMngm Servicio de gestión de comentarios.
     */
    @Autowired
    public CommentsController(CommentsServiceI commentsMngm) {
        this.commentsMngm = commentsMngm;
    }

    /**
     * Maneja la solicitud para agregar un comentario a una publicación.
     *
     * @param publicationID ID de la publicación a la que se agregará el comentario.
     * @param text Texto del comentario.
     * @param image Imagen adjunta al comentario (opcional).
     * @return ResponseEntity con el comentario creado y estado 201 (CREATED).
     */
    @PostMapping("/create/{publicationID}")
    public ResponseEntity<CommentsDto> addComment(@PathVariable Long publicationID,
                                                  @RequestParam(value = "text") String text,
                                                  @RequestParam(value = "image", required = false) MultipartFile image) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        CommentsDto comment = commentsMngm.addComment(publicationID, username, text, image);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

}
