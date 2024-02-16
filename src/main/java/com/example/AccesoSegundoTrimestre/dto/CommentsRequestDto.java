package com.example.AccesoSegundoTrimestre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la solicitud de un comentario.
 *
 * @author Alejandro Ramírez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsRequestDto {

    /**
     * Texto del comentario.
     */
    private String text;

    /**
     * Imagen asociada al comentario.
     */
    private MultipartFile image;

}
