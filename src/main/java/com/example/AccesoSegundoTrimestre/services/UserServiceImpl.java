package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Follow;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.FollowRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz UserServiceI que proporciona operaciones relacionadas con los usuarios.
 *
 * @author Alejandro Ramírez
 */
@Service
public class UserServiceImpl implements UserServiceI{

    private final UserRepositoryI userRepo;
    private final FollowRepositoryI followRepo;

    /**
     * Constructor de UserServiceImpl.
     *
     * @param userRepo Repositorio de usuarios.
     * @param followRepo Repositorio de seguimiento.
     */
    @Autowired
    public UserServiceImpl(UserRepositoryI userRepo, FollowRepositoryI followRepo) {
        this.userRepo = userRepo;
        this.followRepo = followRepo;
    }

    /**
     * Encuentra un usuario por su nombre de usuario y devuelve su DTO.
     *
     * @param username Nombre de usuario del usuario a buscar.
     * @return DTO del usuario encontrado.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
     */
    @Override
    public UserDto findUserByUsernameDTO(String username) {
        User user = findUserByUsername(username);

        UserDto userDto = new UserDto();
        userDto.setUserID(user.getUserID());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setDescription(user.getDescription());
        userDto.setCreationDate(user.getCreateDate());
        return userDto;
    }

    /**
     * Actualiza la descripción de un usuario y devuelve su DTO actualizado.
     *
     * @param username Nombre de usuario del usuario cuya descripción se actualizará.
     * @param userUpdateDto DTO del usuario con la nueva descripción.
     * @return DTO del usuario actualizado.
     */
    @Override
    public UserDto updateUserDescription(String username, UserDto userUpdateDto) {
        User user = findUserByUsername(username);
        if (userUpdateDto.getDescription() != null) {
            user.setDescription(userUpdateDto.getDescription());
        }
        User updatedUser = userRepo.save(user);

        return convertToDto(updatedUser);
    }

    /**
     * Obtiene una lista de seguidores del usuario con el nombre de usuario dado.
     *
     * @param username Nombre de usuario del usuario del que se obtendrán los seguidores.
     * @return Lista de DTO de los seguidores del usuario.
     */
    @Override
    public List<UserDto> getFollowers(String username) {
        User user = findUserByUsername(username);

        List<Follow> userToFollow = followRepo.findByFollowed(user);

        List<UserDto> followers = userToFollow.stream()
                .map(Follow::getFollower)
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return followers;
    }

    /**
     * Obtiene una lista de usuarios a los que sigue el usuario con el nombre de usuario dado.
     *
     * @param username Nombre de usuario del usuario del que se obtendrán los seguidos.
     * @return Lista de DTO de los usuarios seguidos por el usuario.
     */
    @Override
    public List<UserDto> getFollows(String username) {
        User user = findUserByUsername(username);

        List<Follow> userToFollow = followRepo.findByFollower(user);

        List<UserDto> follows = userToFollow.stream()
                .map(Follow::getFollowed)
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return follows;
    }

    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario a buscar.
     * @return El usuario encontrado.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
     */
    private User findUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Convierte un objeto User en un objeto UserDto.
     *
     * @param user Objeto User a convertir.
     * @return Objeto UserDto convertido.
     */
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserID(user.getUserID());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setDescription(user.getDescription());
        userDto.setCreationDate(user.getCreateDate());
        return userDto;
    }
}
