package com.backend.stoa.services;

import com.backend.stoa.entities.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarTodos();
    Cliente encontrarPorCedula(String cedula);
    void guardar(Cliente cliente);
    void eliminar(Cliente cliente);

}
