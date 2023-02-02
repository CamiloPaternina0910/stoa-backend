package com.backend.stoa.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngenieroDtoRequest {

    @NotEmpty(message = "El nombre es requerido")
    private String nombres;

    @NotEmpty(message = "Los apellidos son requeridos")
    private String apellidos;

    @Email(message = "El formato email es requerido")
    @NotNull(message = "El email es requerdio")
    private String email;
}
