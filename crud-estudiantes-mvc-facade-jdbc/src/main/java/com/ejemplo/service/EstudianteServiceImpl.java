package com.ejemplo.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.dao.BaseDatosConexion;
import com.ejemplo.models.Detalle;
import com.ejemplo.models.Estudiante;
import com.ejemplo.models.EstudianteDetalle;
import com.ejemplo.models.Genero;

public class EstudianteServiceImpl implements EstudianteService {
  private static final Logger LOG = Logger.getLogger("EstudianteServiceImpl");
  private static final String SEPARADOR = "\n";

  @Override
  public List<Estudiante> getEstudianteList() {
    List<Estudiante> estudiantes = new ArrayList<>();
    try (
        BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = bdConexion.getConexion();) {
      ResultSet rs = bdConexion.getResultEstudiantes(connection);
      while (rs.next()) {
        estudiantes.add(
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

    return estudiantes;
  }

  @Override
  public void altaEstudiante(Estudiante estudiante, List<String> emails, List<String> telefonos) throws SQLException {
    try (
        BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = bdConexion.getConexion();
    ) {
      bdConexion.altaNuevoEstudiante(connection, estudiante, emails, telefonos);
    } catch (Exception ex) {
      System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
  }

  @Override
  public Detalle getDetalleEstudiante(int idEstudiante) {
    Detalle detalle = null;
      try (
          BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
          Connection connection = bdConexion.getConexion();
          ResultSet rs = bdConexion.getResultDetalleEstudiantes(connection, idEstudiante);
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

  @Override
  public EstudianteDetalle getEstudianteId(int id) {
    EstudianteDetalle estudianteDetalle = null;
    LOG.log(Level.INFO, "ID: {0}", id);
    try (
        BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = bdConexion.getConexion();
        ResultSet rs = bdConexion.getResultEstudianteId(connection, id);
    ) {
      LOG.log(Level.INFO, "ID2: {0}", id);
      int idEstudiante = 0;
      String nombreEstudiante = null;
      String primerApellido = null;
      String segundoApellido = null;
      Genero genero = null;
      int totalAsignaturas = 0;
      LocalDate fechaNacimiento = null;
      BigDecimal becaConcedida = null;
      int idUniversidad = 0;
      String nombreUniversidad = null;
      List<String> telefonos = null;
      List<String> emails = null;
      String valor;
      while (rs.next()) {
        idEstudiante = rs.getInt("idEstudiante");
        nombreEstudiante = rs.getString("nombreEstudiante");
        primerApellido = rs.getString("primerApellido");
        segundoApellido = rs.getString("segundoApellido");
        genero = Genero.valueOf(rs.getString("genero"));
        totalAsignaturas = rs.getInt("totalAsignaturas");
        fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
        becaConcedida = new BigDecimal(rs.getDouble("becaConcedida"));
        idUniversidad = rs.getInt("idUniversidad");
        nombreUniversidad = rs.getString("nombreUniversidad");
        valor = rs.getString("correos");
        if (valor != null) telefonos = Arrays.asList(valor.split(SEPARADOR));
        valor = rs.getString("telefonos");
        if (valor != null) emails = Arrays.asList(valor.split(SEPARADOR));
      }
      // Detalle del estudiante
      estudianteDetalle = EstudianteDetalle.builder()
        .idEstudiante(idEstudiante)
        .nombre(nombreEstudiante)
        .primerApellido(primerApellido)
        .segundoApellido(segundoApellido)
        .genero(genero)
        .totalAsignaturas(totalAsignaturas)
        .fechaNacimiento(fechaNacimiento) 
        .becaConcedida(becaConcedida)
        .idUniversidad(idUniversidad)
        .nombreUniversidad(nombreUniversidad)
        .telefonos(telefonos)
        .correos(emails)
        .build();
      LOG.log(Level.INFO, "Estudiante en detalle: {0}", estudianteDetalle);
    } catch (Exception ex) {
      System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    return estudianteDetalle;
  }

  @Override
  public void actualizacionEstudiante(Estudiante estudiante, List<String> emails, List<String> telefonos) throws SQLException {
    try (
      BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
      Connection connection = bdConexion.getConexion();) {
      bdConexion.actualizacionEstudianteId(connection, estudiante, emails, telefonos);
    } catch (Exception ex) {
      System.getLogger(EstudianteServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
  }
}
