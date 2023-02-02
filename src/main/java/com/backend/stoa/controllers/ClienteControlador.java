package com.backend.stoa.controllers;

import com.backend.stoa.dtos.responses.factories.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cliente")
@Validated
@RequiredArgsConstructor
public class ClienteControlador {

    private ResponseFactory responseFactory;

}
