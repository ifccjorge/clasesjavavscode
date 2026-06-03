<%@ page import="com.ejemplo.models.Departamento"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Formulario</title>
  </head>
  <body>
    <h1>Formulario de Alta/Modificación de empleados</h1>
    <fieldset>
      <legend>Formulario de gestión de empleados</legend>
      <form action="AltaController" method="post">
        <div>
          <label for="nombre">Nombre:</label>
          <input
            type="text"
            id="nombre"
            name="nombre"
            placeholder="su nombre aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="primerApellido">Primer apellido:</label>
          <input
            type="text"
            id="primerApellido"
            name="primerApellido"
            placeholder="su primer apellido aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="segundoApellido">Segundo apellido:</label>
          <input
            type="text"
            id="segundoApellido"
            name="segundoApellido"
            placeholder="su segundo apellido aquí, por favor"
          />
        </div>
        <div>
          <label for="fechaAlta">Fecha de alta:</label>
          <input
            type="date"
            id="fechaAlta"
            name="fechaAlta"
            placeholder="su fecha de alta aquí, por favor"
            required
          />
        </div>
        <div>
          <fieldset>
            <legend>Género</legend>
            <label for="hombre">Hombre:</label>
            <input type="radio" name="genero" id="hombre" value="HOMBRE" required /><br />
            <label for="mujer">Mujer:</label>
            <input type="radio" name="genero" id="mujer" value="MUJER" required /><br />
            <label for="otro">Otro:</label>
            <input type="radio" name="genero" id="otro" value="OTRO" required /><br />
          </fieldset>
        </div>
        <div>
          <label for="salario">Salario:</label>
          <input
            type="text"
            id="salario"
            name="salario"
            placeholder="su salario aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="departamento">Departamento:</label>
          <select id="departamento" name="departamento" required>
            <option></option>
            <%
              List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
              for (Departamento departamento : departamentos) {
              %>
                <option value="<%=departamento.id()%>"><%=departamento.nombre()%></option>
              <%
            }
            %>
          </select>
        </div>
        <div>
          <p>
            <label for="correos">Correos:</label><br/>
            <textarea
              rows="5"
              cols="40"
              id="correos"
              name="correos"
              placeholder="uno o varios separados por punto y coma, por favor"></textarea>
          </p>
          <p>
            <label for="telefonos">Teléfonos:</label><br/>
            <textarea
              rows="5"
              cols="40"
              id="telefonos"
              name="telefonos"
              placeholder="uno o varios separados por punto y coma, por favor"></textarea>
          </p>
        </div>
        <input type="submit" value="Enviar" />
      </form>
    </fieldset>
    <div>
      <p>
        <a href="/crud-empleados-mvc-facade-jdbc/MainController">
          Volver al listado de empleados
        </a>
      </p>
      <p>
        <a href="/crud-empleados-mvc-facade-jdbc">Volver al menú principal</a>
      </p>
    </div>
  </body>
</html>
