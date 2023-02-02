package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.IngenieroDtoRequest;
import com.backend.stoa.dtos.responses.IngenieroDtoResponse;
import com.backend.stoa.entities.Ingeniero;
import com.backend.stoa.utils.DescargaArchivosUtil;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class IngenieroMapperImp implements IngenieroMapper{

    @Override
    public IngenieroDtoResponse toDto(Ingeniero ingeniero) throws FileNotFoundException {
        if(ingeniero == null){
            return null;
        }
        DescargaArchivosUtil descargadorImagen = new DescargaArchivosUtil(ingeniero.getFotoPerfil());
        IngenieroDtoResponse dto = new IngenieroDtoResponse(
                ingeniero.getId_ingeniero(),
                ingeniero.getNombres(),
                ingeniero.getApellidos(),
                ingeniero.getEmail(),
                descargadorImagen.getInputStreamFile());

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

        return ingeniero;
    }
}
