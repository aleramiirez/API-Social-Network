package com.example.AccesoSegundoTrimestre.persistence.model;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PUBLICATION")
@Data
public class Publication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_PUBLICATION_ID")
    private long publicationID;

    @Column(name = "C_USER_ID", unique = true, nullable = false)
    private Long userID;

    @Column(name = "C_TEXT")
    private String text;

    @Column(name = "C_CREATEDATE")
    private Date createDate;

    @Column(name = "C_EDITIONDATE")
    private Date editionDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "C_FK_USER_ID", referencedColumnName = "C_USER_ID")
    private User author;

    public Publication(User author, String text, Date createDate, Date editionDate) {
        this.author = author;
        this.text = text;
        this.createDate = createDate;
        this.editionDate = editionDate;
    }

}
