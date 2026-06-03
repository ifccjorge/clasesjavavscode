<%@ page import="com.ejemplo.models.Detalle"%> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Listado empleados</title>
  </head>
  <body>
    <% Detalle detalle = (Detalle) request.getAttribute("detalle"); %>
    <h1>Lista de empleados</h1>
    <div>
      <p>
        <a href="/crud-empleados-mvc-facade-jdbc/MainController">
          Volver al listado de empleados
        </a>
      </p>
    </div>
    <div>
      <p>
        <a href="/crud-empleados-mvc-facade-jdbc">Volver al menú principal</a>
      </p>
    </div>
    <div>
      <p>
        <a href="/crud-empleados-mvc-facade-jdbc">Volver al menú principal</a>
      </p>
    </div>
  </body>
</html>
