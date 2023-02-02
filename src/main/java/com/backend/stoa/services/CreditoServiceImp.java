package com.backend.stoa.services;

import com.backend.stoa.entities.Cliente;
import com.backend.stoa.entities.Credito;
import com.backend.stoa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditoServiceImp implements CreditoService{

    @Autowired
    private CreditoRepository creditoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Credito> listarTodos() {
        return creditoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Credito> encontrarPorCliente(Cliente cliente) {
        return creditoRepository.findAllByUsuario(cliente);
    }

    @Override
    public Credito encontrarPorId(Long id) {
        return creditoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Credito credito) {
        creditoRepository.save(credito);
    }

    @Override
    @Transactional
    public void eliminar(Credito credito) {
        creditoRepository.delete(credito);
    }
}
