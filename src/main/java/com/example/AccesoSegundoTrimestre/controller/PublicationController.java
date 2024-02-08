package com.example.AccesoSegundoTrimestre.controller;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.services.PublicationServiceI;
import com.example.AccesoSegundoTrimestre.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
@CrossOrigin
public class PublicationController {

    @Autowired
    private PublicationServiceI publicationMgmt;

    @Autowired
    private UserServiceI userMgmt;

    @GetMapping
    private List<PublicationDto> getAllPublications() {
        return publicationMgmt.getAllPublications();
    }

    @GetMapping("/{userName}")
    public List<PublicationDto> getPublicationsByUserName(@PathVariable String username) {
        return publicationMgmt.getPublicationsByUser(username);
    }

    @PostMapping
    private Publication insertPublication(@RequestBody PublicationDto publicationDto) {
        Publication publication = converDtoToPublication(publicationDto);
        return publicationMgmt.insertPublication(publication);
    }

    @PutMapping("/{publicationID}")
    private Publication updatePublication(@PathVariable long publicationID, @RequestBody PublicationDto publicationDto) {

        PublicationDto publicationDto1 = publicationMgmt.getPublicationsById(publicationID);

        Publication publication = converDtoToPublication(publicationDto);

        publication.setPublicationID(publicationID);
        publicationMgmt.editPublication(publication);

        return publication;
    }

    @DeleteMapping("/{publicationID}")
    private void deletePublication(@PathVariable Long id) {
        publicationMgmt.deletePublication(id);
    }

    private Publication converDtoToPublication (PublicationDto publicationDto) {
        return new Publication(publicationDto.getAuthor(), publicationDto.getText(),
                publicationDto.getCreationDate(), publicationDto.getEditionDate());
    }
}
