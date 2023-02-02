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
public class ClienteDtoRequest {

    @NotEmpty(message = "El nombre del cliente es requerido.")
    private String nombre;

    @Email(message = "El formato email es requerido.")
    @NotNull(message = "El email es requerido.")
    private String email;

    @NotEmpty(message = "La cedula del cliente es requerida.")
    private String cedula;

}
