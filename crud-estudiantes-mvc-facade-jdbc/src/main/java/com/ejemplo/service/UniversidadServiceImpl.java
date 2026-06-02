package com.ejemplo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.dao.BaseDatosConexion;
import com.ejemplo.models.Universidad;

public class UniversidadServiceImpl implements UniversidadService {

  @Override
  public List<Universidad> getUniversidadList() {
    List<Universidad> universidades = new ArrayList<>();
    try (
        BaseDatosConexion dbConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection conn = dbConexion.getConexion();
    ) {
      ResultSet rs = dbConexion.getUniversidades(conn);
      while (rs.next()) {
        universidades.add(
          Universidad.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .build()
        );
      }
    } catch (Exception ex) {
      System.getLogger(UniversidadServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    
    return universidades;
  }

}
