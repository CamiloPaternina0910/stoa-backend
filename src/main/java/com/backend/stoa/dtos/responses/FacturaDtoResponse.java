package com.backend.stoa.dtos.responses;

import com.backend.stoa.enums.EstadoFactura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDtoResponse {

    private Long id;

    private String precio;

    private EstadoFactura estado;

    private LocalDate fecha_limite;

}
