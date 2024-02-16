package com.example.AccesoSegundoTrimestre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Objeto de Transferencia de Datos (DTO) que representa las publicaciones de un usuario.
 *
 * @author Alejandro Ramírez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOfUserDto {

    /**
     * Identificador único del autor de las publicaciones.
     */
    private Long athorID;

    /**
     * Nombre de usuario del autor de las publicaciones.
     */
    private String username;

    /**
     * Descripción del autor de las publicaciones.
     */
    private String description;

    /**
     * Lista de publicaciones asociadas con el usuario.
     */
    private List<PublicationDto> publications;

}
