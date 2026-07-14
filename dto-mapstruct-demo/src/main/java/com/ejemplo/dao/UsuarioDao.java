package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entity.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

}
