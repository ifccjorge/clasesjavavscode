package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Estudiante;

public interface CorreoService {

  Correo saveCorreo(Correo correo);
  List<Correo> getAllCorreos();
  boolean existsByEstudiante(Estudiante estudiante);
  void deleteByEstudiante(Estudiante estudiante);
  List<Correo> findByEstudiante(Estudiante estudiante);
}
