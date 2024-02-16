package com.example.AccesoSegundoTrimestre.dto;

import com.example.AccesoSegundoTrimestre.persistence.model.Comments;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la información de una publicación.
 *
 * @author Alejandro Ramírez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDto {

    /**
     * Identificador único de la publicación.
     */
    private long publicationID;

    /**
     * Contenido textual de la publicación.
     */
    private String text;

    /**
     * Fecha y hora en que se creó la publicación.
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime creationDate;

    /**
     * Fecha y hora en que se editó por última vez la publicación.
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime editDate;

    /**
     * Lista de comentarios asociados con la publicación.
     */
    private List<CommentsDto> comments;
}
