package com.backend.stoa.repositories;

import com.backend.stoa.entities.Detalle;
import com.backend.stoa.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {

    List<Detalle> findAllByFactura(Factura factura);

}
