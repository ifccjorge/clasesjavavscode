package com.ejemplo.service;

import java.sql.SQLException;
import java.util.List;

import com.ejemplo.models.Empleado;

public interface EmpleadoService {
  public abstract boolean isConnectionOK() throws SQLException, Exception;
  public abstract List<Empleado> getEmpleadoList();
  public abstract void altaEmpleado(
    Empleado empleado,
    List<String> emails,
    List<String> telefonos
  ) throws SQLException;
}
