package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.ProyectoDtoRequest;
import com.backend.stoa.dtos.responses.ProyectoDto;
import com.backend.stoa.entities.Proyecto;

import java.io.FileNotFoundException;

public interface ProyectoMapper {

    Proyecto aEntidad(ProyectoDtoRequest proyectoDtoRequest);

    ProyectoDto aDto(Proyecto proyecto) throws FileNotFoundException;

}
