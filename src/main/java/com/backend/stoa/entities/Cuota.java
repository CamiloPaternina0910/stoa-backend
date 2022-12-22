package com.backend.stoa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cuotas")
@Getter
@Setter
public class Cuota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cuota;

    @NotEmpty(message = "El nombre de la cuota es requerido")
    private String nombre;

    @NotNull(message = "El precio de la cuota es requerido")
    private BigDecimal precio;

    @ManyToOne
    private Detalle detalle;
}
