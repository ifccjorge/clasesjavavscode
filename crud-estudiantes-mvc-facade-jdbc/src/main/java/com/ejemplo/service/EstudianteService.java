package com.ejemplo.service;

import java.sql.SQLException;
import java.util.List;

import com.ejemplo.models.Detalle;
import com.ejemplo.models.Estudiante;

public interface EstudianteService {
  public abstract List<Estudiante> getEstudianteList();
  public abstract void altaEstudiante(
    Estudiante empleado,
    List<String> emails,
    List<String> telefonos
  ) throws SQLException;
  public abstract Detalle getDetalleEstudiante(int idEmpleado);
}
