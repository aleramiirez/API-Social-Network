package com.example.AccesoSegundoTrimestre.persistence.model;

import com.example.AccesoSegundoTrimestre.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_USERS")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_USER_ID")
    private long userID;

    @Column(name = "C_USERNAME", unique = true)
    private String username;

    @Column(name = "C_EMAIL", unique = true)
    private String email;

    @Column(name = "C_PASSWORD")
    private String password;

    @Column(name = "C_DESCRIPTION")
    private String description;

    @Column(name = "C_CREATEDATE")
    private Date createDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Publication> publicationsList;

    public User(String username, String email, String description, Date createDate, List<Publication> publicationsList) {
        this.username = username;
        this.email = email;
        this.description = description;
        this.createDate = createDate;
        this.publicationsList = publicationsList;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
