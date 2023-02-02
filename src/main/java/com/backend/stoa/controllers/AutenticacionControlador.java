package com.backend.stoa.controllers;

import com.backend.stoa.dtos.requests.LoginRequest;
import com.backend.stoa.dtos.responses.factories.LoginResponseImp;
import com.backend.stoa.dtos.responses.Response;
import com.backend.stoa.dtos.responses.factories.ResponseFactory;
import com.backend.stoa.services.UsuarioService;
import com.backend.stoa.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Validated
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    private ResponseFactory responseFactory;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getClave());
        authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = usuarioService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.createToken(userDetails);
        responseFactory = new LoginResponseImp(token);
        return new ResponseEntity<>(responseFactory.getResponse(), HttpStatus.OK);
    }
}
