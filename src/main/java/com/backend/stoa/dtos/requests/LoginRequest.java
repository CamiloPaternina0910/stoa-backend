package com.backend.stoa.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "El formato email es requerido")
    @NotEmpty(message = "El email es requerido")
    private String username;

    @NotEmpty(message = "La contraseña es requerida")
    private String clave;

}
