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
    List<Estudiante> empleados = new ArrayList<>();
    try (
        BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = bdConexion.getConexion();) {
      ResultSet rs = bdConexion.getResultEstudiantes(connection);
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
        BaseDatosConexion bdConexion = new BaseDatosConexion("cursom", "Temp2026$$");
        Connection connection = bdConexion.getConexion();
    ) {
      bdConexion.altaNuevoEstudiante(connection, empleado, emails, telefonos);
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
        //ResultSet rs = bdConexion.getResultEstudianteId2(connection, id);
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
      while (rs.next()) {
        idEstudiante = rs.getInt(1);
        nombreEstudiante = rs.getString(2);
        primerApellido = rs.getString(3);
        segundoApellido = rs.getString(4);
        genero = Genero.valueOf(rs.getString(5));
        totalAsignaturas = rs.getInt(6);
        fechaNacimiento = rs.getDate(7).toLocalDate();
        becaConcedida = new BigDecimal(rs.getDouble(8));
        idUniversidad = rs.getInt(9);
        nombreUniversidad = rs.getString(10);
        telefonos = Arrays.asList(rs.getString("telefonos").split(SEPARADOR));
        emails = Arrays.asList(rs.getString("emails").split(SEPARADOR));
      }
    //  rs.beforeFirst();
    //  String valor;
    //  while (rs.next()) {
    //    valor = rs.getString("telefono");
    //    if (valor != null) telefonos.add(valor);
    //  }
    //  rs.beforeFirst();
    //  while (rs.next()) {
    //    valor = rs.getString("email");
    //    if (valor != null) emails.add(valor);
    //  }
    //  rs.close();
      // Detalle del empleado
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
