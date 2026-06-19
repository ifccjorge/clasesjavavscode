package com.ejemplo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Estudiante;
import com.ejemplo.entities.Telefono;


public interface TelefonoDao extends JpaRepository<Telefono, Integer> {

  boolean existsByEstudiante(Estudiante estudiante);

  void deleteByEstudiante(Estudiante estudiante);

  List<Telefono> findByEstudiante(Estudiante estudiante);
}
