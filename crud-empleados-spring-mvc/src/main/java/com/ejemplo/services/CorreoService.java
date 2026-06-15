package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Empleado;

public interface CorreoService {

  Correo saveCorreo(Correo correo);
  List<Correo> getAllCorreos();
  boolean existsByEmpleado(Empleado empleado);
  void deleteByEmpleado(Empleado empleado);
  List<Correo> findByEmpleado(Empleado empleado);
}
