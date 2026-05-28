package com.ejemplo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

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
}
