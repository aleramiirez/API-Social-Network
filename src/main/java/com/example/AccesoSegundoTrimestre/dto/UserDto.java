package com.example.AccesoSegundoTrimestre.dto;

import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private String username;
    private String email;
    private String description;
    private Date createDate;
    private List<Publication> publications;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.description = user.getDescription();
        this.createDate = user.getCreateDate();
        this.publications = user.getPublicationsList();
    }
}
