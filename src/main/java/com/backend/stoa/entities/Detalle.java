package com.backend.stoa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "detalles")
@Getter
@Setter
public class Detalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @NotNull(message = "La cantidad del detalle es requerida")
    private int cantidad;

    @NotNull(message = "El precio del detalle es requerido")
    private BigDecimal precio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalle")
    private Set<Cuota> cuotas = new HashSet<>();
    @ManyToOne
    private Factura factura;

    public void agregarCuota(Cuota cuota){
        cuotas.add(cuota);
    }

    public void eliminarCuota(Cuota cuota){
        cuotas.remove(cuota);
    }
}
