package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Correo;

public interface CorreoDao extends JpaRepository<Correo, Integer> {

}
