package com.backend.stoa.dtos.responses;

import com.backend.stoa.entities.Imagen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDto {

    private Long id;

    private String nombre_proyecto;

    private String descripcion;

    private LocalDate fecha_inicio;

    private String lugar;

    private InputStreamResource file;

    private InputStreamResource imagen;

}
