package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.IngenieroDtoRequest;
import com.backend.stoa.dtos.responses.IngenieroDtoResponse;
import com.backend.stoa.entities.Ingeniero;

import java.io.FileNotFoundException;

public interface IngenieroMapper {

    IngenieroDtoResponse toDto(Ingeniero ingeniero) throws FileNotFoundException;
    Ingeniero toEntity(IngenieroDtoRequest ingenieroDtoRequest);

}
