package com.example.AccesoSegundoTrimestre.persistence.repository;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
