package com.backend.stoa.services;

import com.backend.stoa.entities.Cliente;
import com.backend.stoa.entities.Credito;

import java.util.List;

public interface CreditoService {
    List<Credito> listarTodos();
    List<Credito> encontrarPorCliente(Cliente cliente);
    Credito encontrarPorId(Long id);
    void guardar(Credito credito);
    void eliminar(Credito credito);
}
