package com.ejemplo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Estudiante;



public interface CorreoDao extends JpaRepository<Correo, Integer>{

  boolean existsByEstudiante(Estudiante estudiante);

  void deleteByEstudiante(Estudiante estudiante);

  List<Correo> findByEstudiante(Estudiante estudiante);
}
