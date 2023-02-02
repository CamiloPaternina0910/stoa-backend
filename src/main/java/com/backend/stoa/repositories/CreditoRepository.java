package com.backend.stoa.repositories;

import com.backend.stoa.entities.Cliente;
import com.backend.stoa.entities.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Long> {

    List<Credito> findAllByUsuario(Cliente usuario);
}
