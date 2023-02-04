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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth/proyectos")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProyectoControlador {

    private ResponseFactory responseFactory;

    @Autowired
    private ProyectoMapper proyectoMapper;

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/nuevo")
    public ResponseEntity<Response> crearNuevoProyecto(@Valid @RequestBody ProyectoDtoRequest proyectoDtoRequest, @RequestParam("imagen")MultipartFile imagen, @RequestParam("pdf")MultipartFile pdf) throws IOException {
        Proyecto proyecto = proyectoMapper.aEntidad(proyectoDtoRequest);
        SubidaArchivosUtil subidaArchivosUtil = new SubidaArchivosUtil();
        subidaArchivosUtil.setFile(imagen);
        proyecto.setImagen(subidaArchivosUtil.subirImagen());
        subidaArchivosUtil.setFile(pdf);
        proyecto.setArchivo(subidaArchivosUtil.subirPdf());
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

    @GetMapping("/{id}/imagen")
    public void getImagen(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Proyecto proyecto = proyectoService.encontrarPorId(id);
        Path path = Paths.get("uploads/imagenes").resolve(proyecto.getImagen()).toAbsolutePath();
        File file = new File(path.toUri());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition", "attachment;fileName=" + proyecto.getImagen());
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/{id}/pdf")
    public void getPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Proyecto proyecto = proyectoService.encontrarPorId(id);
        Path path = Paths.get("uploads/pdfs").resolve(proyecto.getArchivo()).toAbsolutePath();
        File file = new File(path.toUri());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition", "attachment;fileName=" + proyecto.getArchivo());
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarProyecto(@PathVariable Long id){
        Proyecto proyecto = proyectoService.encontrarPorId(id);
        SubidaArchivosUtil subidaArchivosUtil = new SubidaArchivosUtil();
        subidaArchivosUtil.eliminarImagen(proyecto.getImagen());
        subidaArchivosUtil.eliminarPdf(proyecto.getArchivo());
        proyectoService.eliminar(proyecto);
        responseFactory = new GeneralResponseImp("Proyecto eliminado.", false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }
}
