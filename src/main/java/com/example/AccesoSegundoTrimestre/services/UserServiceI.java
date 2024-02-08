package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.User;

import java.util.List;

public interface UserServiceI {

    void registerUser(User user);

    void loginUser(String username, String password);

    User editDescription(String username, String description);

    UserDto getUserByUsername(String username);

    List<UserDto> getUsersFollowingUser(String username);

    List<UserDto> getUsersFollowedByUser(String username);

}
