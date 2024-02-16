package com.example.AccesoSegundoTrimestre.persistence.repository;

import com.example.AccesoSegundoTrimestre.persistence.model.Follow;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interfaz que define métodos de acceso a datos para la entidad Follow.
 *
 * @author Alejandro Ramírez
 */
@Repository
public interface FollowRepositoryI extends JpaRepository<Follow, Long> {

    /**
     * Elimina los registros de seguimiento por el nombre de usuario del seguidor y el nombre de usuario seguido.
     *
     * @param followerUsername Nombre de usuario del seguidor.
     * @param followedUsername Nombre de usuario seguido.
     */
    @Transactional
    void deleteByFollowerUsernameAndFollowedUsername(String followerUsername, String followedUsername);

    /**
     * Verifica si existe un registro de seguimiento por el seguidor y el seguido.
     *
     * @param follower Seguidor.
     * @param followed Seguido.
     * @return Verdadero si existe un registro de seguimiento, falso de lo contrario.
     */
    boolean existsByFollowerAndFollowed(User follower, User followed);


    /**
     * Obtiene una lista de registros de seguimiento por el usuario seguido.
     *
     * @param followed Usuario seguido.
     * @return Lista de registros de seguimiento del usuario seguido.
     */
    List<Follow> findByFollowed(User followed);

    /**
     * Obtiene una lista de registros de seguimiento por el usuario seguidor.
     *
     * @param follower Usuario seguidor.
     * @return Lista de registros de seguimiento del usuario seguidor.
     */
    List<Follow> findByFollower(User follower);
}
