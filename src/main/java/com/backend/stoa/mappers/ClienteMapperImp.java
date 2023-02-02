package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.ClienteDtoRequest;
import com.backend.stoa.dtos.responses.ClienteDtoResponse;
import com.backend.stoa.dtos.responses.CreditoDtoResponse;
import com.backend.stoa.dtos.responses.FacturaDtoResponse;
import com.backend.stoa.entities.Cliente;
import com.backend.stoa.entities.Credito;
import com.backend.stoa.entities.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapperImp implements ClienteMapper{

    @Autowired
    private FacturaMapper facturaMapper;


    @Override
    public ClienteDtoResponse toDto(Cliente cliente) {
        if(cliente == null){
            return null;
        }

        List<CreditoDtoResponse> creditos = new ArrayList<>();

        for (Credito credito:
                cliente.getCreditos()) {
            creditos.add(CreditoDtoResponse.builder().id(credito.getId_credito()).facturas(obtenerFacturaDtoPorCredito(credito)).build());
        }

        ClienteDtoResponse response = new ClienteDtoResponse(
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getCedula(),
                creditos
        );

        return response;
    }

    @Override
    public Cliente toEntity(ClienteDtoRequest clienteDtoRequest) {
        if(clienteDtoRequest == null){
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(cliente.getNombre());
        cliente.setEmail(clienteDtoRequest.getEmail());
        cliente.setCedula(clienteDtoRequest.getCedula());

        return cliente;
    }

    private List<FacturaDtoResponse> obtenerFacturaDtoPorCredito(Credito credito){
        List<FacturaDtoResponse> facturaDtoResponses = new ArrayList<>();

        for (Factura factura:
                credito.getFacturas()) {
            facturaDtoResponses.add(facturaMapper.toDto(factura));
        }

        return facturaDtoResponses;
    }
}
