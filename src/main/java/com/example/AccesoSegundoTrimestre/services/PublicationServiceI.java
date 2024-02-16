package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;

import java.util.List;

/**
 * Interfaz para definir los servicios relacionados con las publicaciones.
 *
 * @author Alejandro Ramírez
 */
public interface PublicationServiceI {

    /**
     * Agrega una nueva publicación.
     *
     * @param username Nombre de usuario del autor de la publicación.
     * @param text Contenido de la publicación.
     * @return DTO de la publicación agregada.
     */
    PublicationDto addPublication(String username, String text);

    /**
     * Actualiza una publicación existente.
     *
     * @param publicationID ID de la publicación a actualizar.
     * @param username Nombre de usuario del autor de la publicación.
     * @param text Nuevo contenido de la publicación.
     * @return DTO de la publicación actualizada.
     */
    PublicationDto updatePublication(Long publicationID, String username, String text);

    /**
     * Elimina una publicación existente.
     *
     * @param publicationID ID de la publicación a eliminar.
     * @param username Nombre de usuario del autor de la publicación.
     */
    void deletePublication(Long publicationID, String username);

}
