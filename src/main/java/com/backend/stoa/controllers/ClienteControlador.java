package com.backend.stoa.controllers;

import com.backend.stoa.dtos.responses.ClienteDtoResponse;
import com.backend.stoa.dtos.responses.Response;
import com.backend.stoa.dtos.responses.factories.GeneralListResponseImp;
import com.backend.stoa.dtos.responses.factories.ResponseFactory;
import com.backend.stoa.entities.Cliente;
import com.backend.stoa.mappers.ClienteMapper;
import com.backend.stoa.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
@Validated
@RequiredArgsConstructor
public class ClienteControlador {

    private ResponseFactory responseFactory;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    public ResponseEntity<Response> listarTodos(){
        List<ClienteDtoResponse> clientes = new ArrayList<>();

        for (Cliente cliente:
                clienteService.listarTodos()) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        responseFactory = new GeneralListResponseImp<ClienteDtoResponse>(clientes, false, 200);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }


}
