package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.CommentsDto;
import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Comments;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.CommentsRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.PublicationRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Implementación del servicio de comentarios.
 *
 * @author Alejandro Ramírez
 */
@Service
public class CommentsServiceImpl implements CommentsServiceI{

    private final CommentsRepositoryI commentsRepo;

    private final PublicationRepositoryI publicationRepo;

    private final UserRepositoryI userRepo;

    /**
     * Constructor de CommentsServiceImpl.
     *
     * @param commentsRepo    Repositorio de comentarios.
     * @param publicationRepo Repositorio de publicaciones.
     * @param userRepo        Repositorio de usuarios.
     */
    @Autowired
    public CommentsServiceImpl(CommentsRepositoryI commentsRepo, PublicationRepositoryI publicationRepo,
                               UserRepositoryI userRepo) {
        this.commentsRepo = commentsRepo;
        this.publicationRepo = publicationRepo;
        this.userRepo = userRepo;
    }

    /**
     * Agrega un comentario a una publicación.
     *
     * @param publicationID Identificador de la publicación a la que se agrega el comentario.
     * @param authorID      Identificador del autor del comentario.
     * @param text          Texto del comentario.
     * @param image         Imagen asociada al comentario (opcional).
     * @return El comentario agregado en forma de objeto CommentsDto.
     * @throws UsernameNotFoundException Si no se encuentra el usuario con el nombre de usuario proporcionado.
     * @throws EntityNotFoundException   Si no se encuentra la publicación con el ID proporcionado.
     */
    @Override
    public CommentsDto addComment(Long publicationID, String authorID, String text, MultipartFile image) {
        User author = userRepo.findByUsername(authorID).orElseThrow(() ->
                new UsernameNotFoundException("User not found with name: " + authorID));

        Publication publication = publicationRepo.findById(publicationID).orElseThrow(() ->
                new EntityNotFoundException("Post not found with ID: " + publicationID));

        Comments comments = new Comments();
        comments.setAuthorID(author);
        comments.setPublicationID(publication);
        comments.setText(text);

        if (image != null && !image.isEmpty()) {
            try {
                comments.setImage(image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Comments savedComment = commentsRepo.save(comments);

        return convertToDto(savedComment);
    }

    /**
     * Convierte un objeto Comments a un objeto CommentsDto.
     *
     * @param comment Comentario a convertir.
     * @return Comentario convertido a CommentsDto.
     */
    private CommentsDto convertToDto(Comments comment) {
        CommentsDto commentsDto =new CommentsDto();

        commentsDto.setCommentID(comment.getCommentID());
        commentsDto.setText(comment.getText());
        commentsDto.setCreationDate(comment.getCreationDate());

        return commentsDto;
    }
}
