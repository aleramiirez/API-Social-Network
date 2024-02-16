package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.CommentsDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz que define los métodos para el servicio de comentarios.
 *
 * @author Alejandro Ramírez
 */
public interface CommentsServiceI {

    /**
     * Agrega un comentario a una publicación.
     *
     * @param publicationID Identificador de la publicación a la que se agrega el comentario.
     * @param authorID      Identificador del autor del comentario.
     * @param text          Texto del comentario.
     * @param image         Imagen asociada al comentario (opcional).
     * @return El comentario agregado en forma de objeto CommentsDto.
     */
    CommentsDto addComment(Long publicationID, String authorID, String text, MultipartFile image);

}
