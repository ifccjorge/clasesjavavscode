package com.ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  protected static Connection initializeDatabase()
      throws SQLException, ClassNotFoundException {

    String dbDriver = "com.mysql.cj.jdbc.Driver";
    String dbURL = "jdbc:mysql://localhost:3306/";
    String dbName = "empresa-crud-empleados";
    String dbUsername = "cursom";
    String dbPassword = "Temp2026$$";

    Class.forName(dbDriver);
    Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
    return con;
  }
  
}
