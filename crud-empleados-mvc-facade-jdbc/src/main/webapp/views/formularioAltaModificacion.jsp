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
      <form action="#" method="post">
        <label for="nombre">Nombre:</label>
        <input
          id="nombre"
          name="nombre"
          placeholder="su nombre aquí, por favor"
        />
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
