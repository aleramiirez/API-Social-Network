package com.example.AccesoSegundoTrimestre.services;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import com.example.AccesoSegundoTrimestre.persistence.model.User;
import com.example.AccesoSegundoTrimestre.persistence.repository.PublicationRepositoryI;
import com.example.AccesoSegundoTrimestre.persistence.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationServiceI{

    @Autowired
    private PublicationRepositoryI publicationRepo;

    @Autowired
    private UserRepositoryI userRepo;

    @Override
    public List<PublicationDto> getAllPublications() {
        List<Publication> publication = publicationRepo.findAll();
        List<PublicationDto> publicationDtos = new ArrayList<>();

        for (Publication publication1 : publication) {
            PublicationDto publicationDto = new PublicationDto(publication1.getAuthor(),
                    publication1.getText(), publication1.getCreateDate(), publication1.getEditionDate());

            publicationDtos.add(publicationDto);
        }

        return publicationDtos;
    }

    @Override
    public List<PublicationDto> getPublicationsByUser(String username) {
        User user = userRepo.findByUsername(username);
        List<Publication> publication = new ArrayList<>();
        publication.addAll(user.getPublicationsList());

        List<PublicationDto> publicationDtos = new ArrayList<>();

        for (Publication publication1: publication) {
            PublicationDto publicationDto = new PublicationDto(publication1.getAuthor(),
                    publication1.getText(), publication1.getCreateDate(), publication1.getEditionDate());

            publicationDtos.add(publicationDto);
        }

        return publicationDtos;
    }

    @Override
    public PublicationDto getPublicationsById(long publicationID) {
        Optional<Publication> publication = publicationRepo.findById(publicationID);
        if (publication.isPresent()) {
            PublicationDto publicationDto = new PublicationDto(
                    publication.get().getAuthor(),
                    publication.get().getText(),
                    publication.get().getCreateDate(),
                    publication.get().getEditionDate()
            );
        }
        return null;
    }

    @Override
    public List<PublicationDto> getPublicationsByFollowedUsers(String username) {
        return null;
    }

    @Override
    public Publication insertPublication(Publication publication) {
        return publicationRepo.save(publication);
    }

    @Override
    public Publication editPublication(Publication publication) {
        return publicationRepo.save(publication);
    }

    @Override
    public void deletePublication(Long publicationId) {
        publicationRepo.deleteById(publicationId);
    }
}
