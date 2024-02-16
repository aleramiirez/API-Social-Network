package com.example.AccesoSegundoTrimestre.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase que representa un comentario en una publicación.
 *
 * @author Alejandro Ramírez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Coments")
public class Comments implements Serializable {

    /** Identificador único del comentario. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentID;

    /** Publicación a la que pertenece el comentario. */
    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    @JsonBackReference
    private Publication publicationID;

    /** Autor del comentario. */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorID;

    /** Texto del comentario. */
    @Column(name = "text", nullable = false)
    @NotBlank(message = "The publication cannot be empty")
    private String text;

    /** Imagen adjunta al comentario. */
    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    /** Fecha de creación del comentario. */
    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    private LocalDateTime creationDate;

}
