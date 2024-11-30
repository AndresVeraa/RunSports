package com.SpringBoot1.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot1.demo.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
