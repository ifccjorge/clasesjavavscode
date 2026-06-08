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

import com.ejemplo.models.Empleado;

public class DBConexion implements AutoCloseable {
  private final String user;
  private final String password;
  private Connection connection;

  private static final Logger LOG = Logger.getLogger("DBconexion");

  public DBConexion(String user, String password) {
    super();
    this.user = user;
    this.password = password;
  }

  public Connection getConexion() throws SQLException, ClassNotFoundException {
    String urlConnection = "jdbc:mysql://localhost:3306/empresa-crud-empleados";
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
      String query = "SELECT * FROM empleados";
      Statement stmt;
      try {
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
      } catch (SQLException ex) {
        System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public ResultSet getDepartamentos(Connection connection) {
      ResultSet rs = null;
      String query = "SELECT * FROM departamentos ORDER BY nombre";
      Statement stmt;
      try {
        stmt = connection.createStatement();
        rs = stmt.executeQuery(query);
      } catch (SQLException ex) {
        System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public void altaNuevoEmpleado(
      Connection connection,
      Empleado empleado,
      List<String> dirCorreos,
      List<String> numTelefonos
    ) {
      String queryInsertEmpleado = "INSERT INTO `empleados` (`nombre`, `primerApellido`, `segundoApellido`, `fechaAlta`, `genero`, `salario`, `departamentos_id`) VALUES (?, ?, ?, ?, ?, ?, ?)";
      String queryInsertCorreo = "INSERT INTO `correos` (`email`, `empleados_id`) VALUES (?, ?)";
      String queryInsertTelefono = "INSERT INTO `telefonos` (`telefono`, `empleados_id`) VALUES (?, ?)";
      try {
        // Transacción
        connection.setAutoCommit(false);
        // Insertar empleados
        PreparedStatement psInsertEmpleado = connection.prepareStatement(queryInsertEmpleado,
            Statement.RETURN_GENERATED_KEYS);
        psInsertEmpleado.setString(1, empleado.nombre());
        psInsertEmpleado.setString(2, empleado.primerApellido());
        psInsertEmpleado.setString(3, empleado.segundoApellido());
        psInsertEmpleado.setDate(4, Date.valueOf(empleado.fechaAlta()));
        psInsertEmpleado.setString(5, empleado.genero().name());
        psInsertEmpleado.setDouble(6, empleado.salario().doubleValue());
        psInsertEmpleado.setInt(7, empleado.departamentos_id());
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
        System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      } finally {
        try {
          connection.setAutoCommit(true);
        } catch (SQLException ex) {
          System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
      }
    }
    
    public ResultSet getDetallesEmpleados(
      Connection connection,
      int id
    ) {
      ResultSet rs = null;
      String query = "SELECT dep.nombre nombreDpto, tel.telefono numeroTelefono, cor.email email FROM empleados emp INNER JOIN departamentos dep ON emp.departamentos_id = dep.id LEFT OUTER JOIN telefonos tel ON emp.id = tel.empleados_id LEFT OUTER JOIN correos cor ON emp.id = cor.empleados_id WHERE emp.id = ?";
      try {
        PreparedStatement psDetallesEmpleados = connection.prepareStatement(
            query,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
        psDetallesEmpleados.setInt(1, id);
        rs = psDetallesEmpleados.executeQuery();
      } catch (SQLException ex) {
        System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public ResultSet getEmpleadosById(
        Connection connection,
        int id) {
      ResultSet rs = null;
      String query = "select emp.id idEmpleado, emp.nombre nombreEmpleado, emp.primerApellido, emp.segundoApellido, emp.fechaAlta, emp.genero, emp.salario, emp.departamentos_id, dep.id idDpto, dep.nombre nombreDpto, tel.telefono, co.email from empleados emp left join departamentos dep on emp.departamentos_id = dep.id left join correos co on emp.id = co.empleados_id left join telefonos tel on emp.id = tel.empleados_id where emp.id = ?";
      try {
        PreparedStatement psDetallesEmpleados = connection.prepareStatement(
            query,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
        psDetallesEmpleados.setInt(1, id);
        rs = psDetallesEmpleados.executeQuery();
      } catch (SQLException ex) {
        System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      return rs;
    }

    public void actualizaEmpleado(
      Connection connection,
      Empleado empleado,
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
        PreparedStatement psUpdate = connection.prepareStatement(queryUpdate);
        psUpdate.setString(1, empleado.nombre());
        psUpdate.setString(2, empleado.primerApellido());
        psUpdate.setString(3, empleado.segundoApellido());
        psUpdate.setDate(4, Date.valueOf(empleado.fechaAlta()));
        psUpdate.setString(5, empleado.genero().name());
        psUpdate.setBigDecimal(6, empleado.salario());
        psUpdate.setInt(7, empleado.departamentos_id());
        psUpdate.setInt(8, empleado.id());
        psUpdate.executeUpdate();
        // Eliminar teléfonos
        PreparedStatement psEliminarTelefonos = connection.prepareStatement(queryEliminarTelefonos);
        psEliminarTelefonos.setInt(1, empleado.id());
        psEliminarTelefonos.executeUpdate();
        // Insertar teléfonos
        PreparedStatement psInsertarTelefonos = connection.prepareStatement(queryInsertarTelefonos);
        psInsertarTelefonos.setInt(2, empleado.id());
        psInsertarTelefonos.setInt(1, empleado.id());
        for (String telefono : telefonos) {
          psInsertarTelefonos.setString(1, telefono);
          psInsertarTelefonos.addBatch();
        }
        psInsertarTelefonos.executeBatch();
        // Eliminar correos
        PreparedStatement psEliminarCorreos = connection.prepareStatement(queryEliminarCorreos);
        psEliminarCorreos.setInt(1, empleado.id());
        psEliminarCorreos.executeUpdate();
        // Insertar correos
        PreparedStatement psInsertarCorreos = connection.prepareStatement(queryInsertarCorreos);
        psInsertarCorreos.setInt(2, empleado.id());
        psInsertarCorreos.setInt(1, empleado.id());
        for (String email : emails) {
          psInsertarCorreos.setString(1, email);
          psInsertarCorreos.addBatch();
        }
        psInsertarCorreos.executeBatch();
      } catch (SQLException ex) {
          System.getLogger(DBConexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
      
    }

}
