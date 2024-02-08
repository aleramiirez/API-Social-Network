package com.example.AccesoSegundoTrimestre.controller;


import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.services.PublicationServiceI;
import com.example.AccesoSegundoTrimestre.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceI userMgmt;

    @Autowired
    private PublicationServiceI publicationMgmt;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userMgmt.registerUser(user);
    }

    @PostMapping("/login")
    public void loginUser(@RequestParam String username, @RequestParam String password) {
        userMgmt.loginUser(username, password);
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userMgmt.getUserByUsername(username);
    }

    @PutMapping("/{username}")
    private User editDescription(@PathVariable String username, @RequestBody String description) {
        return userMgmt.editDescription(username, description);
    }

    private User converDtoToUser (UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getDescription(), userDto.getEmail(),
                userDto.getCreateDate(), userDto.getPublications());
    }

}
