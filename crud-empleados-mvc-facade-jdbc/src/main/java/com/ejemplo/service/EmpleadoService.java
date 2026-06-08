package com.ejemplo.service;

import java.sql.SQLException;
import java.util.List;

import com.ejemplo.models.Detalle;
import com.ejemplo.models.Empleado;
import com.ejemplo.models.EmpleadoUpdate;

public interface EmpleadoService {
  public abstract boolean isConnectionOK() throws SQLException, Exception;
  public abstract List<Empleado> getEmpleadoList();
  public abstract void altaEmpleado(
    Empleado empleado,
    List<String> emails,
    List<String> telefonos
  ) throws SQLException;
  public abstract Detalle getDetalles(int idEmpleado);
  public abstract EmpleadoUpdate getEmpleadosById(int idEmpleado);
  public abstract void updateEmpleado(
      Empleado empleado,
      List<String> emails,
      List<String> telefonos
  ) throws SQLException;
}
