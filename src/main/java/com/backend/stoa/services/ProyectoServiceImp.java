package com.backend.stoa.services;

import com.backend.stoa.entities.Proyecto;
import com.backend.stoa.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProyectoServiceImp implements ProyectoService{

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Proyecto encontrarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    @Transactional
    public void eliminar(Proyecto eliminar) {
        proyectoRepository.delete(eliminar);
    }
}
