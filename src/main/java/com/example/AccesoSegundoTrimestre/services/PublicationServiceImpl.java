package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.CommentsDto;
import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Comments;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.PublicationRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementación de los servicios relacionados con las publicaciones.
 *
 * @author Alejandro Ramírez
 */
@Service
public class PublicationServiceImpl implements PublicationServiceI{

    private final PublicationRepositoryI publicationRepo;
    private final UserRepositoryI userRepo;

    /**
     * Constructor de la clase.
     *
     * @param userRepo Repositorio de usuarios.
     * @param publicationRepo Repositorio de publicaciones.
     */
    @Autowired
    public PublicationServiceImpl(PublicationRepositoryI publicationRepo, UserRepositoryI userRepo) {
        this.publicationRepo = publicationRepo;
        this.userRepo = userRepo;
    }

    /**
     * Agrega una nueva publicación.
     *
     * @param username Nombre de usuario del autor de la publicación.
     * @param text Contenido de la publicación.
     * @return DTO de la publicación agregada.
     */
    @Override
    public PublicationDto addPublication(String username, String text) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Publication publication = new Publication();
        publication.setText(text);
        publication.setAuthorID(user);

        Publication savedPublication = publicationRepo.save(publication);
        return convertToDto(savedPublication);
    }

    /**
     * Actualiza una publicación existente.
     *
     * @param publicationID ID de la publicación a actualizar.
     * @param username Nombre de usuario del autor de la publicación.
     * @param text Nuevo contenido de la publicación.
     * @return DTO de la publicación actualizada.
     */
    @Override
    public PublicationDto updatePublication(Long publicationID, String username, String text) {
        Publication publication = publicationRepo.findById(publicationID)
                .orElseThrow(() -> new RuntimeException("Publication not found"));
        if (!publication.getAuthorID().getUsername().equals(username)) {
            throw new RuntimeException("You do not have permission to update this post");
        }

        publication.setText(text);
        publication.setEditionDate(LocalDateTime.now());

        Publication updatedPost = publicationRepo.save(publication);
        return convertToDto(updatedPost);
    }

    /**
     * Elimina una publicación existente.
     *
     * @param publicationID ID de la publicación a eliminar.
     * @param username Nombre de usuario del autor de la publicación.
     */
    @Override
    public void deletePublication(Long publicationID, String username) {
        Publication publication = publicationRepo.findById(publicationID)
                .orElseThrow(() -> new RuntimeException("Publication not found"));

        // Verificar que el usuario actual sea el autor de la publicación
        if (!publication.getAuthorID().getUsername().equals(username)) {
            throw new RuntimeException("You do not have permission to delete this post");
        }
        // Eliminar la publicación
        publicationRepo.delete(publication);
    }

    /**
     * Convierte una entidad Publication en su respectivo DTO.
     *
     * @param publication Entidad Publication a convertir.
     * @return DTO de la publicación.
     */
    private PublicationDto convertToDto(Publication publication) {
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setPublicationID(publication.getPublicationID());
        publicationDto.setText(publication.getText());
        publicationDto.setCreationDate(publication.getCreateDate());
        publicationDto.setEditDate(publication.getEditionDate());
        List<CommentsDto> comments = Optional.ofNullable(publication.getComments())
                .map(comments1 -> comments1.stream().map(this::convertToDtoComments).collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        publicationDto.setComments(comments);

        return publicationDto;
    }

    /**
     * Convierte una entidad Comments en su respectivo DTO.
     *
     * @param comment Entidad Comments a convertir.
     * @return DTO del comentario.
     */
    private CommentsDto convertToDtoComments(Comments comment) {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentID(comment.getCommentID());
        commentsDto.setText(comment.getText());
        commentsDto.setCreationDate(comment.getCreationDate());

        return commentsDto;
    }
}
