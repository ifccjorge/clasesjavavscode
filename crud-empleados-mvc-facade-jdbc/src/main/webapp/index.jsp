<%@ page import="java.util.List"%>
<%@ page import="com.ejemplo.enumerado.ApellidoComun"%>
<%@ page import="com.ejemplo.enumerado.GeneroComun"%>
<%@ page import="com.ejemplo.enumerado.NombreFemeninoComun"%>
<%@ page import="com.ejemplo.enumerado.NombreMasculinoComun"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Sistema de gestión de empleados</title>
  </head>
  <body>
    <h1>Bienvenido al sistema de gestión de empleados</h1>
    <p>Utilice el menú para navegar por las diferentes opciones</p>
    <div>
      <a href="MainController">Mostrar listado de empleados</a>
    </div>
    <div>
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
    </div>
    <div>
      <%
        GeneroComun genero = GeneroComun.randomDepartamento();
        out.println("<p>" + genero.name() + "</p>");
        if (genero == GeneroComun.MASCULINO)
          out.println("<p>" + NombreMasculinoComun.randomDepartamento().getTexto() + "</p>");
        else
          out.println("<p>" + NombreFemeninoComun.randomDepartamento().getTexto() + "</p>");
        out.println("<p>" + ApellidoComun.randomDepartamento().getTexto() + "</p>");
      %>
    </div>
  </body>
</html>
