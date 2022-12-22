package com.backend.stoa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @NotEmpty(message = "El nombre del producto es requerido")
    private String nombre;

    @NotEmpty(message = "La descripción del producto es requerida")
    private String descripcion;

    @NotNull(message = "La cantidad del producto es requerida")
    private int cantidad;

    @NotNull(message = "EL precio del producto es requerido")
    private BigDecimal precio;

    @OneToMany(mappedBy = "producto")
    private Set<Credito> creditos = new HashSet<>();

    @ManyToOne
    private Proyecto proyecto;
}
