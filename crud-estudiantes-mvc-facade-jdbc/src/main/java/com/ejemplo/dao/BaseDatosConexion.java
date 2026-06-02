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
    
    public ResultSet getEmpleados(Connection connection) {
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

    public ResultSet getUniversidades(Connection connection) {
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

}
