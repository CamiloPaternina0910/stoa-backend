package com.backend.stoa.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngenieroDtoResponse {

    private Long id;

    private String nombres;

    private String apellidos;

    private String email;

    private InputStreamResource imagen;

}
