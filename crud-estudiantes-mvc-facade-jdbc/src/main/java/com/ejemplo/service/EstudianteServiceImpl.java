package com.ejemplo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.dao.BaseDatosConexion;
import com.ejemplo.models.Detalle;
import com.ejemplo.models.Estudiante;
import com.ejemplo.models.Genero;

public class EstudianteServiceImpl implements EstudianteService {
  private static final Logger LOG = Logger.getLogger("EstudianteServiceImpl");

  @Override
  public List<Estudiante> getEstudianteList() {
    List<Estudiante> empleados = new ArrayList<>();
    try (
        BaseDatosConexion dbConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = dbConexion.getConexion();) {
      ResultSet rs = dbConexion.getResultEstudiantes(connection);
      while (rs.next()) {
        empleados.add(
            Estudiante.builder()
                .id(rs.getInt("id"))
                .nombre(rs.getString("nombre"))
                .primerApellido(rs.getString("primerApellido"))
                .segundoApellido(rs.getString("segundoApellido"))
                .genero(Genero.valueOf(rs.getString("genero")))
                .totalAsignaturas(rs.getInt("totalAsignaturas"))
                .fechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate())
                .becaConcedida(rs.getBigDecimal("becaConcedida"))
                .universidades_id(rs.getInt("universidades_id"))
                .build());
      }
    } catch (Exception ex) {
      System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }

    return empleados;
  }

  @Override
  public void altaEstudiante(Estudiante empleado, List<String> emails, List<String> telefonos) throws SQLException {
    try (
        BaseDatosConexion dbConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = dbConexion.getConexion();
    ) {
      dbConexion.altaNuevoEstudiante(connection, empleado, emails, telefonos);
    } catch (Exception ex) {
      System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
  }

  @Override
  public Detalle getDetalleEstudiante(int idEmpleado) {
    Detalle detalle = null;
      try (
          BaseDatosConexion dbConexion = new BaseDatosConexion("cursom", "Temp2026$$");
          Connection connection = dbConexion.getConexion();
          ResultSet rs = dbConexion.getResultDetalleEstudiantes(connection, idEmpleado);
      ) {
        String nombreUniversidad = null;
        List<String> telefonos = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        while (rs.next()) {
          switch (rs.getInt(1)) {
            case 1 -> nombreUniversidad = rs.getString(2);
            case 2 -> telefonos.add(rs.getString(2));
            case 3 -> emails.add(rs.getString(2));
          }
        }
        rs.close();
        // Detalle del estudiante
        detalle = Detalle.builder()
          .nombreUniversidad(nombreUniversidad)
          .telefonos(telefonos)
          .correos(emails)
          .build();
        LOG.log(Level.INFO, "Detalle: {0}", detalle);
      } catch (Exception ex) {
        System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }


    return detalle;
  }
}
