package com.example.AccesoSegundoTrimestre.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa una publicación en el sistema.
 *
 * @author Alejandro Ramírez
 */
@Entity
@Data
@Table(name = "Publications")
@NoArgsConstructor
@AllArgsConstructor
public class Publication implements Serializable{

    /** Identificador único de la publicación. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publication_id")
    private Long publicationID;

    /** Autor de la publicación. */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private User authorID;

    /** Texto de la publicación. */
    @Column(name = "text", nullable = false)
    @NotBlank(message = "The publication cannot be empty")
    private String text;

    /** Fecha de creación de la publicación. */
    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime createDate;

    /** Fecha de edición de la publicación. */
    @CreationTimestamp
    @Column(name = "edition_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime editionDate;

    /** Lista de comentarios asociados a la publicación. */
    @OneToMany(mappedBy = "publicationID", cascade = CascadeType.ALL)
    private List<Comments> comments;
}
