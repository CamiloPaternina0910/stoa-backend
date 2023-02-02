package com.backend.stoa.entities;

import com.backend.stoa.enums.EstadoCredito;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "creditos")
@Getter
@Setter
public class Credito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_credito;

    @NotNull(message = "El estado del crédito es requerido")
    private EstadoCredito estado;

    @NotNull(message = "La descripcion del crédito es requerida")
    private String descripcion;

    @NotNull(message = "El número de cuotas es requerida")
    private byte cuotas;

    private byte interes;

    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente usuario;

    @ManyToOne
    private Producto producto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "credito")
    private Set<Factura> facturas = new HashSet<>();

    public void agregarFactura(Factura factura){
        facturas.add(factura);
    }

    public void eliminarFactura(Factura factura){
        facturas.remove(factura);
    }
}
