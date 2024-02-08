package com.example.AccesoSegundoTrimestre.persistence.repository;

import com.example.AccesoSegundoTrimestre.dto.PublicationDto;
import com.example.AccesoSegundoTrimestre.persistence.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepositoryI extends JpaRepository<Publication, Long> {

    @Override
    Optional<Publication> findById(Long id);

}
