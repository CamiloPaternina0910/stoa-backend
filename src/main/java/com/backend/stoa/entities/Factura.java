package com.backend.stoa.entities;

import com.backend.stoa.enums.EstadoFactura;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "facturas")
@Getter
@Setter
public class Factura implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;

    @NotNull(message = "El estado de la factura es requerido")
    private EstadoFactura estado;

    @NotNull(message = "La fecha de activación de la factura es requerido")
    private LocalDate fechaActiva;

    @NotNull(message = "La fecha de vencimiento de la factura es requerido")
    private LocalDate fechaVencimiento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Set<Detalle> detalles = new HashSet<>();

    @ManyToOne
    private Credito credito;

    public void agregarDetalle(Detalle detalle){
        detalles.add(detalle);
    }

    public void eliminarDetalle(Detalle detalle){
        detalles.remove(detalle);
    }
}
