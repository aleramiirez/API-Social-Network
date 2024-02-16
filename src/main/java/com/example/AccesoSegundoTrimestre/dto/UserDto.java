package com.example.AccesoSegundoTrimestre.dto;

import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * DTO (Data Transfer Object) para representar la información de un usuario.
 *
 * @author Alejandro Ramírez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    /**
     * Identificador único del usuario.
     */
    private Long userID;

    /**
     * Nombre de usuario del usuario.
     */
    private String username;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Descripción del usuario.
     */
    private String description;

    /**
     * Fecha y hora en que se creó el usuario.
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime creationDate;

}
