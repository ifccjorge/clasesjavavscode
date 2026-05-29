package com.ejemplo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.dao.DBConexion;
import com.ejemplo.models.Departamento;

public class DepartamentoServiceImpl implements DepartamentoService {

  @Override
  public List<Departamento> getDepartamentoList() {
    List<Departamento> departamentos = new ArrayList<>();
    try (
      DBConexion dbConexion = new DBConexion("cursom", "Temp2026$$");
      Connection connection = dbConexion.getConexion();
    ) {
      ResultSet rs = dbConexion.getDepartamentos(connection);
      while (rs.next()) {
        departamentos.add(
          Departamento.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .build()
        );
      }
    } catch (Exception ex) {
      System.getLogger(EmpleadoServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    return departamentos;
  }

}
