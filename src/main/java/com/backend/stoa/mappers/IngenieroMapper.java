package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.IngenieroDtoRequest;
import com.backend.stoa.dtos.responses.IngenieroDtoResponse;
import com.backend.stoa.entities.Ingeniero;


public interface IngenieroMapper {

    IngenieroDtoResponse toDto(Ingeniero ingeniero);
    Ingeniero toEntity(IngenieroDtoRequest ingenieroDtoRequest);

}
