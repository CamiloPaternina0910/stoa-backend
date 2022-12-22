package com.backend.stoa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotEmpty(message = "El nombre del usuario es requerido")
    private String nombre;

    @NotEmpty(message = "EL apellido del usuario es requerido")
    private String apellidos;

    @NotEmpty(message = "La fecha de nacimiento del usuario es requerida")
    private LocalDate fechaNacimiento;

    @Email(message = "El formato email es requerido")
    @NotEmpty(message = "El email es requerido")
    private String username;

    @NotEmpty(message = "La contraseña es requerida")
    private String clave;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rol> roles = new HashSet<>();

    public void agregarRol(Rol rol){
        roles.add(rol);
    }

    public void eliminarRol(Rol rol){
        roles.remove(rol);
    }
}

