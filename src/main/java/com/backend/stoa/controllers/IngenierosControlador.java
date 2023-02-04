package com.backend.stoa.controllers;

import com.backend.stoa.dtos.requests.IngenieroDtoRequest;
import com.backend.stoa.dtos.responses.IngenieroDtoResponse;
import com.backend.stoa.dtos.responses.Response;
import com.backend.stoa.dtos.responses.factories.GeneralListResponseImp;
import com.backend.stoa.dtos.responses.factories.GeneralResponseImp;
import com.backend.stoa.dtos.responses.factories.ObjectResponseImp;
import com.backend.stoa.dtos.responses.factories.ResponseFactory;
import com.backend.stoa.entities.Ingeniero;
import com.backend.stoa.mappers.IngenieroMapper;
import com.backend.stoa.services.IngenieroService;
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
@RequestMapping("api/v1/auth/ingenieros")
@Validated
@RequiredArgsConstructor
public class IngenierosControlador {

    private ResponseFactory responseFactory;

    @Autowired
    private IngenieroService ingenieroService;

    @Autowired
    private IngenieroMapper ingenieroMapper;

    @GetMapping("/todos")
    public ResponseEntity<Response> listarIngenieros() throws FileNotFoundException {
        List<IngenieroDtoResponse> responses = new ArrayList<>();

        for (Ingeniero ingeniero:
                ingenieroService.listarTodos()){
            responses.add(ingenieroMapper.toDto(ingeniero));
        }

        responseFactory = new GeneralListResponseImp<IngenieroDtoResponse>(responses, false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Response> crearNuevoIngeniero(@Valid @RequestBody IngenieroDtoRequest request, @RequestParam("imagen") MultipartFile fotoPerfil) throws IOException {
        Ingeniero ingeniero = ingenieroMapper.toEntity(request);
        SubidaArchivosUtil subida = new SubidaArchivosUtil();
        subida.setFile(fotoPerfil);
        ingeniero.setFotoPerfil(subida.subirImagen());
        ingenieroService.guardar(ingeniero);
        responseFactory = new GeneralResponseImp("Ingeniero creado.", false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarIngeniero(@PathVariable Long id){
        Ingeniero ingeniero = ingenieroService.encontrarPorId(id);
        SubidaArchivosUtil subidaArchivosUtil = new SubidaArchivosUtil();
        subidaArchivosUtil.eliminarImagen(ingeniero.getFotoPerfil());
        ingenieroService.eliminar(ingeniero);
        responseFactory = new GeneralResponseImp("Ingeniero eliminado.", false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> buscarIngeniero(@PathVariable Long id){
        responseFactory = new ObjectResponseImp(ingenieroService.encontrarPorId(id), 200, false);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }
}
