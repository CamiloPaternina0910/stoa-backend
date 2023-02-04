package com.backend.stoa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingenieros")
@Getter
@Setter
public class Ingeniero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingeniero;

    @NotEmpty(message = "El nombre del ingeniero es requerido")
    private String nombres;

    @NotEmpty(message = "El apellido del ingeniero es requerido")
    private String apellidos;

    @NotEmpty(message = "El cargo es requerido")
    private String cargo;

    @Email(message = "Debe contener el formato email")
    @NotEmpty(message = "El email es requerido")
    private String email;

    @NotEmpty(message = "La foto de perfil es requerida")
    private String fotoPerfil;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingenieros")
    private Set<Proyecto> proyectos = new HashSet<>();

    void agregarProyecto(Proyecto proyecto){
        proyectos.add(proyecto);
    }

    void eliminarProyecto(Proyecto proyecto){
        proyectos.remove(proyecto);
    }
}
