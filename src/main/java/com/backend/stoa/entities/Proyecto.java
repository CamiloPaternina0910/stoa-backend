package com.backend.stoa.entities;

import com.backend.stoa.enums.EstadoProyecto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proyectos")
@Getter
@Setter
public class Proyecto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proyecto;

    @NotEmpty(message = "El nombre del proyecto es requerido")
    private String nombre;

    @NotEmpty(message = "La descripción del proyecto es requerida")
    private String descripcion;

    @NotNull(message = "La fecha de inicio del proyecto es requerida")
    private LocalDate fechaInicio;

    @NotNull(message = "El lugar de ejecución del proyecto es requerido")
    private String lugar;

    @NotNull(message = "El estado del proyecto es requerido")
    private EstadoProyecto estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Set<Producto> productos = new HashSet<>();

    private String imagen;

    private String archivo;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Ingeniero> ingenieros = new HashSet<>();

    public void agregarProducto(Producto producto){
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto){
        productos.remove(producto);
    }

    public void agregarIngeniero(Ingeniero ingeniero){
        ingenieros.add(ingeniero);
    }

    public void eliminarIngeniero(Ingeniero ingeniero){
        ingenieros.remove(ingeniero);
    }
}
