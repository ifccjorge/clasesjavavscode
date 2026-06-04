<%@ page import="com.ejemplo.models.Detalle"%>
<%@ page import="com.ejemplo.models.Empleado"%>
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
      Empleado empleado = (Empleado) request.getAttribute("empleado");
    %>
    <h1>Detalles del empleado</h1>
    <div>
      <p>
        <ul>
          <li><b>Nombre:</b> <%=empleado.nombre()%></li>
          <li><b>Primer apellido: </b><%=empleado.primerApellido()%></li>
          <li><b>Segundo apellido: </b><%=empleado.segundoApellido()%></li>
          <li><b>Fecha de alta: </b><%=empleado.fechaAlta().format(formatters)%></li>
          <li><b>Género:</b> <%=empleado.genero().name()%></li>
          <li><b>Salario:</b> <%=numberFormat.format(empleado.salario())%></li>
          <li><b>Departamento:</b> <%=detalle.nombreDpto()%></li>
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
  </body>
</html>
