package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;

public interface TelefonoService {

  Telefono saveTelefono(Telefono telefono);
  List<Telefono> getAllTelefono();
  boolean existsByEmpleado(Empleado empleado);
  void deleteByEmpleado(Empleado empleado);
  List<Telefono> findByEmpleado(Empleado empleado);
}
