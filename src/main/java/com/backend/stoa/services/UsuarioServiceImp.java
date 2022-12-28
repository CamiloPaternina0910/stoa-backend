package com.backend.stoa.services;

import com.backend.stoa.entities.Rol;
import com.backend.stoa.entities.Usuario;
import com.backend.stoa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null)
                throw new UsernameNotFoundException("Usuario no encontrado");

        List<GrantedAuthority> roles = new ArrayList<>();

        for (Rol role : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getNombreRol()));
        }

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getClave(), roles);
    }
}
