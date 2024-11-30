package com.SpringBoot1.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot1.demo.entidades.Usuario;
import com.SpringBoot1.demo.servicio.UsuarioServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")

public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("usuarios")
    public List<Usuario> findAll() {
        return usuarioServicio.findAll();
    }

    @PostMapping("usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioServicio.create(usuario);
        if (newUsuario == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    @PostMapping("iniciar")

    public ResponseEntity<String> IniciarSesion(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioServicio.autenticar(usuario.getEmail(), usuario.getContrasena());

        if (autenticado) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

}
