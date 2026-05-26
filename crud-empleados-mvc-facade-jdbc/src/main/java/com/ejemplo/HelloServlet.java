package com.ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  @SuppressWarnings("CallToPrintStackTrace")
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
    Map<Integer, String> empleados = new HashMap<>();
    try(
      Connection con = DatabaseConnection.initializeDatabase();
      Statement sentencia = con.createStatement();
      ResultSet resultado = sentencia.executeQuery("SELECT id, nombre FROM departamentos")
    ) {
      while (resultado.next()) {
        empleados.put(resultado.getInt("id"), resultado.getString("nombre"));
      }
      resultado.close();
      con.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h1>Hello from Servlet!</h1>");
    empleados.forEach(
      (clave, valor) -> out.println("<p>" + clave + ":" + valor + "</p>")
    );
    //Departamento departamento = Departamento.randomDepartamento();
    //System.out.println(departamento);
  }
}
