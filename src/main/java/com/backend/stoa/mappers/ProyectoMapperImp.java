package com.backend.stoa.mappers;

import com.backend.stoa.dtos.requests.ProyectoDtoRequest;
import com.backend.stoa.dtos.responses.ProyectoDto;
import com.backend.stoa.entities.Proyecto;
import com.backend.stoa.utils.DescargaArchivosUtil;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ProyectoMapperImp implements ProyectoMapper{

    @Override
    public Proyecto aEntidad(ProyectoDtoRequest proyectoDtoRequest) {
        if(proyectoDtoRequest == null){
            return null;
        }

        Proyecto proyecto = new Proyecto();
        proyecto.setDescripcion(proyectoDtoRequest.getDescripcion());
        proyecto.setNombre(proyecto.getNombre());
        proyecto.setFechaInicio(proyectoDtoRequest.getFecha());
        proyecto.setLugar(proyecto.getLugar());

        return proyecto;
    }

    @Override
    public ProyectoDto aDto(Proyecto proyecto) throws FileNotFoundException {
        if(proyecto == null){
            return null;
        }

        DescargaArchivosUtil descagadorImagen = new DescargaArchivosUtil(proyecto.getImagen());
        DescargaArchivosUtil descargadorPdf = new DescargaArchivosUtil(proyecto.getArchivo());

        ProyectoDto proyectoDto = new ProyectoDto();
        proyectoDto.setId(proyecto.getId_proyecto());
        proyectoDto.setNombre_proyecto(proyecto.getNombre());
        proyectoDto.setDescripcion(proyecto.getDescripcion());
        proyectoDto.setImagen(descagadorImagen.getInputStreamFile());
        proyectoDto.setFile(descargadorPdf.getInputStreamFile());
        proyectoDto.setLugar(proyectoDto.getLugar());

        return proyectoDto;
    }
}
