package com.ejemplo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.ejemplo.models.Estudiante;

public class BaseDatosConexion implements AutoCloseable {
  private final String user;
  private final String password;
  private Connection connection;

  private static final Logger LOG = Logger.getLogger("DBconexion");

  public BaseDatosConexion(String user, String password) {
    super();
    this.user = user;
    this.password = password;
  }

  public Connection getConexion() throws SQLException, ClassNotFoundException {
    String urlConnection = "jdbc:mysql://localhost:3306/universidad";
    Properties info = new Properties();

    info.put("user", this.user);
    info.put("password", this.password);
    
    Class.forName("com.mysql.cj.jdbc.Driver");
    this.connection = DriverManager.getConnection(urlConnection, info);
    LOG.info(() -> "Conectado exitosamente en " + urlConnection);

    return this.connection;
  }

    @Override
    public void close() throws Exception {
      this.connection.close();
    }
    
    public ResultSet getResultEstudiantes(Connection connection) {
      ResultSet rs = null;
      String query = "SELECT * FROM estudiantes";
      Statement stmt;
      try {
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
      } catch (SQLException ex) {
        System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public ResultSet getResultUniversidades(Connection connection) {
      ResultSet rs = null;
      String query = "SELECT * FROM universidades ORDER BY nombre";
      Statement stmt;
      try {
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
      } catch (SQLException ex) {
        System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public void altaNuevoEstudiante(
      Connection connection,
      Estudiante estudiante,
      List<String> dirCorreos,
      List<String> numTelefonos
    ) {
      String queryInsertEmpleado = "INSERT INTO `estudiantes` (`nombre`, `primerApellido`, `segundoApellido`, `genero`, `totalAsignaturas`, `fechaNacimiento`, `becaConcedida`, `universidades_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      String queryInsertCorreo = "INSERT INTO `correos` (`email`, `estudiantes_id`) VALUES (?, ?)";
      String queryInsertTelefono = "INSERT INTO `telefonos` (`telefono`, `estudiantes_id`) VALUES (?, ?)";
      try {
        // Transacción
        connection.setAutoCommit(false);
        // Insertar empleados
        PreparedStatement psInsertEmpleado = connection.prepareStatement(queryInsertEmpleado, Statement.RETURN_GENERATED_KEYS);
        psInsertEmpleado.setString(1, estudiante.nombre());
        psInsertEmpleado.setString(2, estudiante.primerApellido());
        psInsertEmpleado.setString(3, estudiante.segundoApellido());
        psInsertEmpleado.setString(4, estudiante.genero().name());
        psInsertEmpleado.setInt(5, estudiante.totalAsignaturas());
        psInsertEmpleado.setDate(6, Date.valueOf(estudiante.fechaNacimiento()));
        psInsertEmpleado.setDouble(7, estudiante.becaConcedida().doubleValue());
        psInsertEmpleado.setInt(8, estudiante.universidades_id());
        int totalFilas = psInsertEmpleado.executeUpdate();
        if (totalFilas != 0) {
          // Recuperar índice
          long lastInsertedId = 0L;
          ResultSet rsIdEmpleado = psInsertEmpleado.getGeneratedKeys();
          if (rsIdEmpleado.next())
            lastInsertedId = rsIdEmpleado.getLong(1);
          // Insertar correos
          if (dirCorreos != null && !dirCorreos.isEmpty()) {
            PreparedStatement psInsertCorreo = connection.prepareStatement(queryInsertCorreo);
            psInsertCorreo.setInt(2, Math.toIntExact(lastInsertedId));
            for (String email : dirCorreos) {
              psInsertCorreo.setString(1, email);
              psInsertCorreo.addBatch();
            }
            psInsertCorreo.executeBatch();
          }
          // Insertar teléfonos
          if (numTelefonos != null && !numTelefonos.isEmpty()) {
            PreparedStatement psInsertTelefono = connection.prepareStatement(queryInsertTelefono);
            psInsertTelefono.setInt(2, Math.toIntExact(lastInsertedId));
            for (String telefono : numTelefonos) {
              psInsertTelefono.setString(1, telefono);
              psInsertTelefono.addBatch();
            }
            psInsertTelefono.executeBatch();
          }
        connection.commit();
        }
      } catch (SQLException ex) {
          System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      } finally {
          try {
            connection.setAutoCommit(true);
          } catch (SQLException ex) {
            System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
          }
      }
    }

    public ResultSet getResultDetalleEstudiantes(
        Connection connection,
        int id) {
      ResultSet rs = null;
      String query = "SELECT 1, nombre FROM universidades WHERE id = (SELECT universidades_id FROM estudiantes WHERE id = ?) UNION SELECT 2, email FROM correos WHERE estudiantes_id = ? UNION SELECT 3, telefono FROM telefonos WHERE estudiantes_id = ?";
      try {
        PreparedStatement psDetalleEstudiantes = connection.prepareStatement(query);
        psDetalleEstudiantes.setInt(1, id);
        psDetalleEstudiantes.setInt(2, id);
        psDetalleEstudiantes.setInt(3, id);
        rs = psDetalleEstudiantes.executeQuery();
      } catch (SQLException ex) {
        System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public ResultSet getResultEstudianteId(
        Connection connection,
        int id
    ) {
      ResultSet rs = null;
      String query = "SELECT e.id idEstudiante, e.nombre, e.primerApellido, e.segundoApellido, e.genero, e.totalAsignaturas, e.fechaNacimiento, e.becaConcedida, e.universidades_id idUniversidad, u.nombre nombreUniversidad, GROUP_CONCAT(DISTINCT c.email SEPARATOR '\\n') correos, GROUP_CONCAT(DISTINCT t.telefono SEPARATOR '\\n') telefonos FROM estudiantes e INNER JOIN universidades u ON e.universidades_id = u.id LEFT OUTER JOIN correos c ON e.id = c.estudiantes_id LEFT OUTER JOIN telefonos t ON e.id = t.estudiantes_id WHERE e.id = ? GROUP BY e.id, e.nombre, e.primerApellido, e.segundoApellido, e.genero, e.totalAsignaturas, e.fechaNacimiento, e.becaConcedida, e.universidades_id, u.nombre";
      try {
        PreparedStatement psEstudiante = connection.prepareStatement(query);
        psEstudiante.setInt(1, id);
        rs = psEstudiante.executeQuery();
      } catch (SQLException ex) {
        System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public void actualizacionEstudianteId(
      Connection connection,
      Estudiante estudiante,
      List<String> emails,
      List<String> telefonos
    ) {
      String queryUpdate = "UPDATE `empleados` SET `nombre` = ?, `primerApellido` = ?, `segundoApellido` = ?, `fechaAlta` = ?, `genero` = ?, `salario` = ?, `departamentos_id` = ? WHERE (`id` = ?)";
      String queryEliminarTelefonos = "DELETE FROM telefonos WHERE empleados_id = ?";
      String queryInsertarTelefonos = "INSERT INTO `telefonos` (`telefono`, `empleados_id`) VALUES (?, ?)";
      String queryEliminarCorreos = "DELETE FROM correos WHERE empleados_id = ?";
      String queryInsertarCorreos = "INSERT INTO `correos` (`email`, `empleados_id`) VALUES (?, ?)";
      try {
        // Actualizar empleados
        PreparedStatement psActualizarEstudiante = connection.prepareStatement(queryUpdate);
        psActualizarEstudiante.setString(1, estudiante.nombre());
        psActualizarEstudiante.setString(2, estudiante.primerApellido());
        psActualizarEstudiante.setString(3, estudiante.segundoApellido());
        psActualizarEstudiante.setString(5, estudiante.genero().name());
        psActualizarEstudiante.setInt(7, estudiante.totalAsignaturas());
        psActualizarEstudiante.setDate(4, Date.valueOf(estudiante.fechaNacimiento()));
        psActualizarEstudiante.setBigDecimal(6, estudiante.becaConcedida());
        psActualizarEstudiante.setInt(7, estudiante.universidades_id());
        psActualizarEstudiante.setInt(8, estudiante.id());
        psActualizarEstudiante.executeUpdate();
        // Eliminar teléfonos
        PreparedStatement psEliminarTelefonos = connection.prepareStatement(queryEliminarTelefonos);
        psEliminarTelefonos.setInt(1, estudiante.id());
        psEliminarTelefonos.executeUpdate();
        // Insertar teléfonos
        PreparedStatement psInsertarTelefonos = connection.prepareStatement(queryInsertarTelefonos);
        psInsertarTelefonos.setInt(2, estudiante.id());
        psInsertarTelefonos.setInt(1, estudiante.id());
        for (String telefono : telefonos) {
          psInsertarTelefonos.setString(1, telefono);
          psInsertarTelefonos.addBatch();
        }
        psInsertarTelefonos.executeBatch();
        // Eliminar correos
        PreparedStatement psEliminarCorreos = connection.prepareStatement(queryEliminarCorreos);
        psEliminarCorreos.setInt(1, estudiante.id());
        psEliminarCorreos.executeUpdate();
        // Insertar correos
        PreparedStatement psInsertarCorreos = connection.prepareStatement(queryInsertarCorreos);
        psInsertarCorreos.setInt(2, estudiante.id());
        psInsertarCorreos.setInt(1, estudiante.id());
        for (String email : emails) {
          psInsertarCorreos.setString(1, email);
          psInsertarCorreos.addBatch();
        }
        psInsertarCorreos.executeBatch();
      } catch (SQLException ex) {
        System.getLogger(BaseDatosConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
    }
}
