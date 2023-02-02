package com.backend.stoa.services;

import com.backend.stoa.entities.Ingeniero;
import com.backend.stoa.repositories.IngenieroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngenieroServiceImp implements IngenieroService{

    @Autowired
    private IngenieroRepository ingenieroRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Ingeniero> listarTodos() {
        return ingenieroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Ingeniero encontrarPorId(Long id) {
        return ingenieroRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Ingeniero ingeniero) {
        ingenieroRepository.save(ingeniero);
    }

    @Override
    @Transactional
    public void eliminar(Ingeniero ingeniero) {
        ingenieroRepository.delete(ingeniero);
    }
}
