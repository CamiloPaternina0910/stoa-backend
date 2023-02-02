package com.backend.stoa.services;

import com.backend.stoa.entities.Cliente;
import com.backend.stoa.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrarPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
