package com.backend.stoa.mappers;

import com.backend.stoa.dtos.responses.FacturaDtoResponse;
import com.backend.stoa.entities.Factura;

public interface FacturaMapper {

    FacturaDtoResponse toDto(Factura factura);

}
