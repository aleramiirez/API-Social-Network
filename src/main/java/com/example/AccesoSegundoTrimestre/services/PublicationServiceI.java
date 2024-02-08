package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;

import java.util.List;

public interface PublicationServiceI {

    List<PublicationDto> getAllPublications();

    List<PublicationDto> getPublicationsByUser(String username);

    PublicationDto getPublicationsById(long publicationID);

    List<PublicationDto> getPublicationsByFollowedUsers(String username);

    Publication insertPublication(Publication publicationDTO);

    Publication editPublication(Publication publication);

    void deletePublication(Long publicationId);

}
