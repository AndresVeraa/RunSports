package com.SpringBoot1.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot1.demo.entidades.Usuario;
import com.SpringBoot1.demo.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Usuario create(Usuario usuario) {
        Usuario newUsuario = usuarioRepositorio.save(usuario);
        return newUsuario;
    }

    public Usuario iniciarSesion(String email, String password) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            return usuario;
        }
        return null;
    }

    public boolean autenticar(String email, String password) {

        Usuario usuario = usuarioRepositorio.findByEmail(email);

        if (usuario != null) {
            // Aquí se compara la contraseña en texto plano. En producción, debes
            // encriptarla.
            return usuario.getContrasena().equals(password);
        }
        return false;
    }

}
