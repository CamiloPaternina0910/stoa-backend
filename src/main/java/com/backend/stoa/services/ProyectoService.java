package com.backend.stoa.services;

import com.backend.stoa.entities.Proyecto;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> listarTodos();
    Proyecto encontrarPorId(Long id);
    void guardar(Proyecto proyecto);
    void eliminar(Proyecto eliminar);
}
