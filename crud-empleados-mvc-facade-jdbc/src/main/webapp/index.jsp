<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <body>
    <h2 style="color: green; background-color: silver">
      <%= "Hello World!" %>
    </h2>
    <% String mensaje = "HOLA";%>
    <p><%=mensaje%></p>
    <% List<String> nombres = List.of("Juan", "Maria", "Pedro", "Ana");
      for (String nombre : nombres) {
        out.println("<p>" + nombre + "</p>");
      }
    %>
  </body>
</html>
