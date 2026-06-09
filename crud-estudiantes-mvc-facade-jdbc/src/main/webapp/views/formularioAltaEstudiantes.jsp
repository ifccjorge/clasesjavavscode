<%@ page import="com.ejemplo.models.EstudianteDetalle"%>
<%@ page import="com.ejemplo.models.Universidad"%>
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
    <h1>Formulario de Alta/Modificación de estudiantes</h1>
    <fieldset>
      <legend>Formulario de gestión de estudiantes</legend>
      <form action="altaestudiante" method="post">
        <%
          EstudianteDetalle estudianteDetalle = (EstudianteDetalle) request.getAttribute("estudianteDetalle");
        %>
        <input type="hidden" name="idEstudiante" value="<%=estudianteDetalle != null ? estudianteDetalle.idEstudiante() : 0%>">
        <div>
          <label for="nombre">Nombre:</label>
          <input
            type="text"
            size="60"
            id="nombre"
            name="nombre"
            value="<%=estudianteDetalle != null ? estudianteDetalle.nombre() : ""%>"
            placeholder="su nombre aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="primerApellido">Primer apellido:</label>
          <input
            type="text"
            size="60"
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
            size="60"
            id="segundoApellido"
            name="segundoApellido"
            placeholder="su segundo apellido aquí, por favor"
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
          <label for="totalAsignaturas">Total de asignaturas:</label>
          <input
            type="text"
            size="60"
            id="totalAsignaturas"
            name="totalAsignaturas"
            placeholder="su total de asignaturas aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="fechaNacimiento">Fecha de alta:</label>
          <input
            type="date"
            id="fechaNacimiento"
            name="fechaNacimiento"
            placeholder="su fecha de nacimiento aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="becaConcedida">Beca concedida:</label>
          <input
            type="text"
            size="30"
            id="becaConcedida"
            name="becaConcedida"
            placeholder="su beca concedida aquí, por favor"
            required
          />
        </div>
        <div>
          <label for="universidad">Universidades:</label>
          <select id="universidad" name="universidad" required>
            <option></option>
            <%
              List<Universidad> universidades = (List<Universidad>) request.getAttribute("universidades");
              for (Universidad universidad : universidades) {
              %>
                <option value="<%=universidad.id()%>"><%=universidad.nombre()%></option>
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
        <a href="/crud-estudiantes-mvc-facade-jdbc/listaestudiantes">
          Volver al listado de estudiantes
        </a>
      </p>
      <p>
        <a href="/crud-estudiantes-mvc-facade-jdbc">Volver al menú principal</a>
      </p>
    </div>
  </body>
</html>
