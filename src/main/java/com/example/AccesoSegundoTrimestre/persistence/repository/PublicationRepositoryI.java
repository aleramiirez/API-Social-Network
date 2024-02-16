package com.example.AccesoSegundoTrimestre.persistence.repository;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define métodos de acceso a datos para la entidad de publicación.
 *
 * @author Alejandro Ramírez
 */
@Repository
public interface PublicationRepositoryI extends JpaRepository<Publication, Long> {


    /**
     * Busca publicaciones por el autor.
     *
     * @param author Autor de la publicación.
     * @return Lista de publicaciones del autor.
     */
    List<Publication> findByAuthorID(User author);

}
