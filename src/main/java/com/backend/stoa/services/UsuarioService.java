package com.backend.stoa.services;

import com.backend.stoa.entities.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UsuarioService {

    public List<Usuario> listarTodos();
    public Usuario buscarPorEmail(String username);
    public void guardar(Usuario usuario);
    public void eliminar(Usuario usuario);

    public UserDetails loadUserByUsername(String username);

}
