package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.ClienteDtoRequest;
import com.backend.stoa.dtos.responses.ClienteDtoResponse;
import com.backend.stoa.entities.Cliente;

public interface ClienteMapper {

    ClienteDtoResponse toDto(Cliente cliente);
    Cliente toEntity(ClienteDtoRequest clienteDtoRequest);

}
