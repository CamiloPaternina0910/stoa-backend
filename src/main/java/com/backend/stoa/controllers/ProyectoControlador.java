package com.backend.stoa.controllers;

import com.backend.stoa.dtos.requests.ProyectoDtoRequest;
import com.backend.stoa.dtos.responses.ProyectoDto;
import com.backend.stoa.dtos.responses.Response;
import com.backend.stoa.dtos.responses.factories.GeneralListResponseImp;
import com.backend.stoa.dtos.responses.factories.GeneralResponseImp;
import com.backend.stoa.dtos.responses.factories.ResponseFactory;
import com.backend.stoa.entities.Proyecto;
import com.backend.stoa.mappers.ProyectoMapper;
import com.backend.stoa.services.ProyectoService;
import com.backend.stoa.utils.SubidaArchivosUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth/proyectos")
@Validated
@RequiredArgsConstructor
public class ProyectoControlador {

    private ResponseFactory responseFactory;

    @Autowired
    private ProyectoMapper proyectoMapper;

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/nuevo")
    public ResponseEntity<Response> crearNuevoProyecto(@Valid @RequestBody ProyectoDtoRequest proyectoDtoRequest, @RequestParam("imagen")MultipartFile imagen, @RequestParam("pdf")MultipartFile pdf) throws IOException {
        Proyecto proyecto = proyectoMapper.aEntidad(proyectoDtoRequest);
        proyecto.setImagen(subirArchivo(imagen));
        proyecto.setArchivo(subirArchivo(pdf));
        proyectoService.guardar(proyecto);
        responseFactory = new GeneralResponseImp("Proyecto creado.", false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<Response> obtenerTodos() throws FileNotFoundException {
        List<ProyectoDto> proyectoDtos = new ArrayList<>();

        for (Proyecto proyecto:
             proyectoService.listarTodos()) {
            proyectoDtos.add(proyectoMapper.aDto(proyecto));
        }
        responseFactory = new GeneralListResponseImp<ProyectoDto>(proyectoDtos, false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarProyecto(@PathVariable Long id){
        Proyecto proyecto = proyectoService.encontrarPorId(id);
        proyectoService.eliminar(proyecto);
        responseFactory = new GeneralResponseImp("Proyecto eliminado.", false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }

    public String subirArchivo(MultipartFile file) throws IOException {
        SubidaArchivosUtil subidaArchivosUtil = new SubidaArchivosUtil(file);
        return subidaArchivosUtil.copyFileToUploads();
    }
}
