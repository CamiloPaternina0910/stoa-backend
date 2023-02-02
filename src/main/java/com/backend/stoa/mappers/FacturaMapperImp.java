package com.backend.stoa.mappers;

import com.backend.stoa.dtos.responses.FacturaDtoResponse;
import com.backend.stoa.entities.Detalle;
import com.backend.stoa.entities.Factura;
import com.backend.stoa.repositories.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class FacturaMapperImp implements FacturaMapper{

    @Autowired
    private DetalleRepository detalleRepository;

    @Override
    public FacturaDtoResponse toDto(Factura factura) {
        if (factura == null) {
            return null;
        }

        FacturaDtoResponse response = new FacturaDtoResponse();
        response.setId(factura.getId_factura());

        BigDecimal precio = new BigDecimal(0);
        for (Detalle detalle :
                obtenerDetallesPorFactura(factura)) {
            precio = precio.add(detalle.getPrecio());
        }

        response.setPrecio(precio.toString());
        return response;
    }

    private List<Detalle> obtenerDetallesPorFactura(Factura factura){
        return detalleRepository.findAllByFactura(factura);
    }
}
