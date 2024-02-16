package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.CommentsDto;
import com.example.AccesoSegundoTrimestre.dto.PostOfUserDto;
import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Comments;
import com.example.AccesoSegundoTrimestre.persistence.model.Follow;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.FollowRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.PublicationRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para gestionar las publicaciones de los usuarios.
 *
 * @author Alejandro Ramírez
 */
@Service
public class PostOfUserServiceImpl implements PostOfUserServiceI{

    private final UserRepositoryI userRepo;

    private final PublicationRepositoryI publicationRepo;

    private final FollowRepositoryI followRepo;

    /**
     * Constructor de la clase.
     *
     * @param userRepo Repositorio de usuarios.
     * @param publicationRepo Repositorio de publicaciones.
     * @param followRepo Repositorio de relaciones de seguimiento.
     */
    @Autowired
    public PostOfUserServiceImpl(UserRepositoryI userRepo, PublicationRepositoryI publicationRepo,
                                 FollowRepositoryI followRepo) {
        this.userRepo = userRepo;
        this.publicationRepo = publicationRepo;
        this.followRepo = followRepo;
    }

    /**
     * Obtiene todas las publicaciones de todos los usuarios.
     *
     * @return Lista de DTOs de las publicaciones de los usuarios.
     */
    @Override
    public List<PostOfUserDto> getAllPublications() {
        List<Publication> publications = publicationRepo.findAll();
        return publications.stream()
                .map(publication -> convertToDto(publication.getAuthorID(), List.of(publication)))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las publicaciones de un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return DTO de las publicaciones del usuario.
     * @throws UsernameNotFoundException Si el usuario no se encuentra.
     */
    @Override
    public PostOfUserDto getPublicationByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "User not found with username: " + username));

        List<Publication> publications =publicationRepo.findByAuthorID(user);

        return convertToDto(user, publications);
    }

    /**
     * Obtiene las publicaciones de los usuarios seguidos por un usuario.
     *
     * @param username Nombre de usuario.
     * @return Lista de DTOs de las publicaciones de los usuarios seguidos.
     * @throws UsernameNotFoundException Si el usuario no se encuentra.
     */
    @Override
    public List<PostOfUserDto> getPublicationsOfFollowedUser(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "User not found with username: " + username));

        List<User> followedUsers = getFollowedUsers(user);

        List<PostOfUserDto> postOfUserDtos = new ArrayList<>();

        for (User followedUser : followedUsers) {
            List<Publication> publications = publicationRepo.findByAuthorID(followedUser);

            List<PublicationDto> publicationDtos = publications.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            PostOfUserDto postOfUserDto = new PostOfUserDto();

            postOfUserDto.setAthorID(followedUser.getUserID());
            postOfUserDto.setUsername(followedUser.getUsername());
            postOfUserDto.setDescription(followedUser.getDescription());
            postOfUserDto.setPublications(publicationDtos);

            postOfUserDtos.add(postOfUserDto);
        }

        return postOfUserDtos;
    }

    /**
     * Obtiene la lista de usuarios seguidos por un usuario.
     *
     * @param user Usuario.
     * @return Lista de usuarios seguidos.
     */
    private List<User> getFollowedUsers(User user) {
        List<Follow> followRelation = followRepo.findByFollower(user);

        return followRelation.stream()
                .map(Follow::getFollowed)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un usuario y sus publicaciones en un DTO.
     *
     * @param user Usuario.
     * @param publications Lista de publicaciones del usuario.
     * @return DTO del usuario con sus publicaciones.
     */
    private PostOfUserDto convertToDto(User user, List<Publication> publications) {
        PostOfUserDto postOfUserDto = new PostOfUserDto();
        postOfUserDto.setAthorID(user.getUserID());
        postOfUserDto.setUsername(user.getUsername());
        postOfUserDto.setDescription(user.getDescription());

        List<PublicationDto> publicationDtos = publications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        postOfUserDto.setPublications(publicationDtos);

        return postOfUserDto;
    }

    /**
     * Convierte una publicación en un DTO.
     *
     * @param publication Publicación.
     * @return DTO de la publicación.
     */
    private PublicationDto convertToDto(Publication publication) {
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setPublicationID(publication.getPublicationID());
        publicationDto.setText(publication.getText());
        publicationDto.setCreationDate(publication.getCreateDate());
        publicationDto.setEditDate(publication.getEditionDate());

        List<CommentsDto> commentsDto = Optional.ofNullable(publication.getComments())
                .map(comments -> comments.stream().map(this::convertToDtoComments).collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        publicationDto.setComments(commentsDto);

        return publicationDto;
    }

    /**
     * Convierte un comentario en un DTO.
     *
     * @param comment Comentario.
     * @return DTO del comentario.
     */
    private CommentsDto convertToDtoComments(Comments comment) {
        CommentsDto commentsDTO = new CommentsDto();
        commentsDTO.setCommentID(comment.getCommentID());
        commentsDTO.setText(comment.getText());
        commentsDTO.setImage(comment.getImage());
        commentsDTO.setCreationDate(comment.getCreationDate());
        return commentsDTO;
    }
}
