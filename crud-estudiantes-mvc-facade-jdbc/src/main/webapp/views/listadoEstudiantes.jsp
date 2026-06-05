<%@ page import="com.ejemplo.models.Estudiante"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Listado de estudiantes</title>
  </head>
  <body>
    <%
      Locale local = Locale.of("es", "ES");
      DateTimeFormatter formatters = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' uuuu", local);
      NumberFormat numberFormat = NumberFormat.getCurrencyInstance(local);
      numberFormat.setMinimumFractionDigits(2);
      numberFormat.setMaximumFractionDigits(2);
      List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
    %>
    <h1>Lista de estudiantes de la universidad</h1>
    <div>
      <p>
        <a href="altaestudiante" title="Muestra el formulario de alta/modificación de estudiantes">
          Alta de un estudiante
        </a>
      </p>
      <table border="3" cellpadding="6" cellspacing="0">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Primer apellido</th>
            <th>Segundo apellido</th>
            <th>Genero</th>
            <th>Total asignatura</th>
            <th>Fecha de nacimiento</th>
            <th>Beca concedida</th>
            <th>Universidad</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <%
            for (Estudiante estudiante : estudiantes) {
              %>
              <tr>
                <td><%=estudiante.nombre()%></td>
                <td><%=estudiante.primerApellido()%></td>
                <td><%=estudiante.segundoApellido()%></td>
                <td><%=estudiante.genero().name()%></td>
                <td><%=estudiante.totalAsignaturas()%></td>
                <td><%=estudiante.fechaNacimiento().format(formatters)%></td>
                <td><%=numberFormat.format(estudiante.becaConcedida())%></td>
                <td><%=estudiante.universidades_id()%></td>
                <td><a href="detalleestudiante?idEstudiante=<%=estudiante.id()%>">Detalles</a></td>
              </tr>
              <%
            }
          %>
        </tbody>
      </table>      
    </div>
    <div>
      <p><a href="/crud-estudiantes-mvc-facade-jdbc">Volver al menú principal</a></p>
    </div>
  </body>
</html>
