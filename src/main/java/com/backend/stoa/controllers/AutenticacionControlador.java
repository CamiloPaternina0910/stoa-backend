package com.backend.stoa.controllers;

import com.backend.stoa.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Validated
@RequiredArgsConstructor
public class AutenticacionControlador {

    @Autowired
    private UsuarioService usuarioService;

    
}
