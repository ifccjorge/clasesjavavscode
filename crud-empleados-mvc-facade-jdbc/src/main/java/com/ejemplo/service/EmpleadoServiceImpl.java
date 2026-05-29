package com.ejemplo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.dao.DBConexion;
import com.ejemplo.models.Empleado;
import com.ejemplo.models.Genero;

public class EmpleadoServiceImpl implements EmpleadoService {

  @Override
  public boolean isConnectionOK() throws SQLException, Exception {

    boolean conectionOK = false;

    try (DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
            Connection conn = dbConexion.getConexion();
    ) {
        if (conn != null) conectionOK = true;
    } catch (SQLException | ClassNotFoundException ex) {
        System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
      return conectionOK;
  }

  @Override
  public List<Empleado> getEmpleadoList() {
    List<Empleado> empleados = new ArrayList<>();
    try (
      DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
      Connection connection = dbConexion.getConexion();
    ) {
      ResultSet rs = dbConexion.getEmpleados(connection);
      while (rs.next()) {
        empleados.add(
          Empleado.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .primerApellido(rs.getString("primerApellido"))
            .segundoApellido(rs.getString("segundoApellido"))
            .fechaAlta(rs.getDate("fechaAlta").toLocalDate())
            .genero(Genero.valueOf(rs.getString("genero")))
            .salario(rs.getBigDecimal("salario"))
            .departamentos_id(rs.getInt("departamentos_id"))
            .build()
        );
      }
    } catch (Exception ex) {
      System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    
    return empleados;
  }

}
