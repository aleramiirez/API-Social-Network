package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceI{

    @Autowired
    private UserRepositoryI userRepo;


    @Override
    public void registerUser(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Nombre de usuario ya registrado");
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Correo electrónico ya registrado");
        }

        // Guarda el usuario en la base de datos
        User user1 = new User(user.getUsername(), user.getEmail(), user.getPassword());

        // Codifica la contraseña antes de almacenarla
        user1.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepo.save(user1);
    }

    @Override
    public void loginUser(String username, String password) {
        // Buscar al usuario por nombre de usuario
        User userEntity = userRepo.findByUsername(username);

        // Verificar la contraseña
        if (BCrypt.checkpw(password, userEntity.getPassword())) {
            // Si las credenciales son correctas, puedes devolver el usuario o un DTO según tus necesidades.

        } else {
            // Si las credenciales son incorrectas, puedes lanzar una excepción o devolver null.
            throw new IllegalArgumentException("Credenciales incorrectas");
        }
    }

    @Override
    public User editDescription(String username, String description) {
        User user = userRepo.findByUsername(username);
        user.setDescription(description);
        return userRepo.save(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);

        UserDto userDto = new UserDto(user.getUsername(), user.getDescription(), user.getEmail(),
                user.getCreateDate(), user.getPublicationsList());
        return userDto;
    }

    @Override
    public List<UserDto> getUsersFollowingUser(String username) {
        return null;
    }

    @Override
    public List<UserDto> getUsersFollowedByUser(String username) {
        return null;
    }

}
