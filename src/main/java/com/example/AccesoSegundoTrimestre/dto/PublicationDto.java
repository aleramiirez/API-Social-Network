package com.example.AccesoSegundoTrimestre.dto;

import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDto {

    private User author;
    private String text;
    private Date creationDate;
    private Date editionDate;

    public PublicationDto(Publication publication) {
        this.author = publication.getAuthor();
        this.text = publication.getText();
        this.creationDate = new Date();
        this.editionDate = new Date();
    }

}
