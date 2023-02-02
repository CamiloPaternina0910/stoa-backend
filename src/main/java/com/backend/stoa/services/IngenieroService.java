package com.backend.stoa.services;

import com.backend.stoa.entities.Ingeniero;

import java.util.List;

public interface IngenieroService {

    List<Ingeniero> listarTodos();
    Ingeniero encontrarPorId(Long id);
    void guardar(Ingeniero ingeniero);
    void eliminar(Ingeniero ingeniero);

}
