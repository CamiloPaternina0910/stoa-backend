package com.backend.stoa.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDtoRequest {

    @NotEmpty(message = "El nombre del proyecto es requerido")
    private String nombre_proyecto;

    @NotEmpty(message = "La descripcion del proyecto es requerida")
    private String descripcion;

    private LocalDate fecha;

    private String lugar;
}
