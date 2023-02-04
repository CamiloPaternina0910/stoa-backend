package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.IngenieroDtoRequest;
import com.backend.stoa.dtos.responses.IngenieroDtoResponse;
import com.backend.stoa.entities.Ingeniero;
import org.springframework.stereotype.Component;

@Component
public class IngenieroMapperImp implements IngenieroMapper{

    @Override
    public IngenieroDtoResponse toDto(Ingeniero ingeniero){
        if(ingeniero == null){
            return null;
        };
        IngenieroDtoResponse dto = new IngenieroDtoResponse(
                ingeniero.getId_ingeniero(),
                ingeniero.getNombres(),
                ingeniero.getApellidos(),
                ingeniero.getCargo(),
                ingeniero.getEmail());

        return dto;
    }

    @Override
    public Ingeniero toEntity(IngenieroDtoRequest ingenieroDtoRequest) {
        if(ingenieroDtoRequest == null){
            return null;
        }

        Ingeniero ingeniero = new Ingeniero();
        ingeniero.setNombres(ingenieroDtoRequest.getNombres());
        ingeniero.setApellidos(ingenieroDtoRequest.getApellidos());
        ingeniero.setEmail(ingenieroDtoRequest.getEmail());
        ingeniero.setCargo(ingeniero.getCargo());

        return ingeniero;
    }
}
