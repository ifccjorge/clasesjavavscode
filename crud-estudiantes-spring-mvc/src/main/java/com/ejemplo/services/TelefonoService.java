package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Estudiante;
import com.ejemplo.entities.Telefono;

public interface TelefonoService {

  Telefono saveTelefono(Telefono telefono);
  List<Telefono> getAllTelefono();
  boolean existsByEstudiante(Estudiante estudiante);
  void deleteByEstudiante(Estudiante estudiante);
  List<Telefono> findByEstudiante(Estudiante estudiante);
}
