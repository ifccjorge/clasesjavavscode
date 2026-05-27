<%@ page import="com.ejemplo.models.Empleado"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <body>
    <%
      Locale local = Locale.of("es", "ES");
      DateTimeFormatter formatters = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' uuuu", local);
      NumberFormat numberFormat = NumberFormat.getCurrencyInstance(local);
      numberFormat.setMinimumFractionDigits(2);
      numberFormat.setMaximumFractionDigits(2);
      List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
    %>
    <h1>Lista de empleados</h1>
    <div>
      <table border="4" cellpadding="8" cellspacing="0">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Primer apellido</th>
            <th>Segundo apellido</th>
            <th>Fecha de alta</th>
            <th>Genero</th>
            <th>Salario</th>
          </tr>
        </thead>
        <tbody>
          <%
            for (Empleado empleado : empleados) {
              %>
              <tr>
                <td><%=empleado.nombre()%></td>
                <td><%=empleado.primerApellido()%></td>
                <td><%=empleado.segundoApellido()%></td>
                <td><%=empleado.fechaAlta().format(formatters)%></td>
                <td><%=empleado.genero().name()%></td>
                <td><%=numberFormat.format(empleado.salario())%></td>
              </tr>
              <%
            }
          %>
        </tbody>
      </table>      
    </div>
    <div>
      <p><a href="/crud-empleados-mvc-facade-jdbc">Volver al menú principal</a></p>
    </div>
  </body>
</html>
