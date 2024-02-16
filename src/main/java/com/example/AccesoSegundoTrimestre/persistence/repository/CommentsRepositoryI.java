package com.example.AccesoSegundoTrimestre.persistence.repository;

import com.example.AccesoSegundoTrimestre.persistence.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define métodos de acceso a datos para la entidad Comments.
 *
 * @author Alejandro Ramírez
 */
@Repository
public interface CommentsRepositoryI extends JpaRepository<Comments, Long> {

}
