package com.ejemplo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.dao.BaseDatosConexion;
import com.ejemplo.models.Estudiante;
import com.ejemplo.models.Genero;

public class EstudianteServiceImpl implements EstudianteService {

  @Override
  public List<Estudiante> getEstudianteList() {
    List<Estudiante> empleados = new ArrayList<>();
    try (
        BaseDatosConexion dbConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = dbConexion.getConexion();
    ) {
      ResultSet rs = dbConexion.getEmpleados(connection);
      while (rs.next()) {
        empleados.add(
          Estudiante.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .primerApellido(rs.getString("primerApellido"))
            .segundoApellido(rs.getString("segundoApellido"))
            .genero(Genero.valueOf(rs.getString("genero")))
            .totalAsignaturas(rs.getInt("universidades_id"))
            .fechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate())
            .becaConcedida(rs.getBigDecimal("becaConcedida"))
            .universidades_id(rs.getInt("universidades_id"))
            .build()
        );
      }
    } catch (Exception ex) {
      System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    
    return empleados;
  }

}
