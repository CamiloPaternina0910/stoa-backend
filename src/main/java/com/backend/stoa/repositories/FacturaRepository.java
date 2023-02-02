package com.backend.stoa.repositories;

import com.backend.stoa.entities.Credito;
import com.backend.stoa.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findAllByCredito(Credito credito);
}
