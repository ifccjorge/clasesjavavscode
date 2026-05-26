<%@ page import="java.util.List"%>
<%@ page import="com.ejemplo.enumerado.ApellidoComun"%>
<%@ page import="com.ejemplo.enumerado.Genero"%>
<%@ page import="com.ejemplo.enumerado.NombreFemeninoComun"%>
<%@ page import="com.ejemplo.enumerado.NombreMasculinoComun"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <body>
    <h2 style="color: green; background-color: silver">
      <%= "Conexión a base de datos" %>
    </h2>
    <% String mensaje = "HOLA";%>
    <p><%=mensaje%></p>
    <% List<String> nombres = List.of("Juan", "Maria", "Pedro", "Ana");
      for (String nombre : nombres) {
        out.println("<p>" + nombre + "</p>");
      }
    %>
    <h1>Bienvenido al sistema de gestión de empleados</h1>
    <p>Utilice el menú para navegar por las diferentes opciones</p>
    <div>
      <a href="MainController">Mostrar listado de empleados</a>
    </div>
    <div>
    <%
      Genero genero = Genero.randomDepartamento();
      out.println("<p>" + genero.name() + "</p>");
      if (genero == Genero.MASCULINO)
        out.println("<p>" + NombreMasculinoComun.randomDepartamento().getTexto() + "</p>");
      else
        out.println("<p>" + NombreFemeninoComun.randomDepartamento().getTexto() + "</p>");
      out.println("<p>" + ApellidoComun.randomDepartamento().getTexto() + "</p>");
    %>
    </div>
  </body>
</html>
