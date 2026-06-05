<%@ page import="com.ejemplo.models.Detalle"%>
<%@ page import="com.ejemplo.models.Estudiante"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Locale"%>
<%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Detalles del empleado</title>
  </head>
  <body>
    <%
      Locale local = Locale.of("es", "ES");
      DateTimeFormatter formatters = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' uuuu", local);
      NumberFormat numberFormat = NumberFormat.getCurrencyInstance(local);
      numberFormat.setMinimumFractionDigits(2);
      numberFormat.setMaximumFractionDigits(2);
      Detalle detalle = (Detalle) request.getAttribute("detalle");
      Estudiante estudiante = (Estudiante) request.getAttribute("estudiante");
    %>
    <h1>Detalles del estudiante</h1>
    <div>
      <p>
        <ul>
          <li><b>Nombre:</b> <%=estudiante.nombre()%></li>
          <li><b>Primer apellido: </b><%=estudiante.primerApellido()%></li>
          <li><b>Segundo apellido: </b><%=estudiante.segundoApellido()%></li>
          <li><b>Género:</b> <%=estudiante.genero().name()%></li>
          <li><b>Total de asignaturas:</b> <%=estudiante.totalAsignaturas()%></li>
          <li><b>Fecha de nacimiento: </b><%=estudiante.fechaNacimiento().format(formatters)%></li>
          <li><b>Beca concedida:</b> <%=numberFormat.format(estudiante.becaConcedida())%></li>
          <li><b>Universidad:</b> <%=detalle.nombreUniversidad()%></li>
          <li><b>Correos:</b>
            <ul>
              <%
                for (String correo : detalle.correos()) {
                %>
                  <%if (correo != null) {%>
                    <li><%=correo%></li>
                  <%
                  }
                }
              %>
            </ul>
          </li>
          <li><b>Teléfonos:</b>
            <ul>
              <%
                for (String telefono : detalle.telefonos()) {
                %>
                  <%if (telefono != null) {%>
                    <li><%=telefono%></li>
                  <%
                  }
                }
              %>
            </ul>
          </li>
        </ul>
      </p>
    </div>
    <div>
      <p>
        <a href="/crud-estudiantes-mvc-facade-jdbc/listaestudiantes">
          Volver al listado de empleados
        </a>
      </p>
    </div>
    <div>
      <p>
        <a href="/crud-estudiantes-mvc-facade-jdbc">Volver al menú principal</a>
      </p>
    </div>
  </body>
</html>
