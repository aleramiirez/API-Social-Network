package com.example.AccesoSegundoTrimestre.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase que representa una relación de seguimiento entre usuarios en el sistema.
 *
 * @author Alejandro Ramírez
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Follow")
public class Follow implements Serializable{

    /** Identificador único de la relación de seguimiento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    /** Usuario que sigue a otro usuario. */
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    /** Usuario seguido por otro usuario. */
    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private User followed;

}
