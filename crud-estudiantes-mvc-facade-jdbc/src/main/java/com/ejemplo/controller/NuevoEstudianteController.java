package com.ejemplo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.Estudiante;
import com.ejemplo.models.Genero;
import com.ejemplo.models.Universidad;
import com.ejemplo.service.EstudianteService;
import com.ejemplo.service.EstudianteServiceImpl;
import com.ejemplo.service.UniversidadService;
import com.ejemplo.service.UniversidadServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/altaestudiante")
public class NuevoEstudianteController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("NuevoEstudianteController");
  private static final String SEPARADOR = ";";

  /**
   *  @see HttpServlet#HttpServlet()
   */
  public NuevoEstudianteController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    UniversidadService universidadService = new UniversidadServiceImpl();
    List<Universidad> universidades = universidadService.getUniversidadList();
    request.setAttribute("universidades", universidades);
    request.getRequestDispatcher("views/formularioAltaEstudiantes.jsp").forward(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Recepción de parámetros
    String nombre = request.getParameter("nombre");
    String primerApellido = request.getParameter("primerApellido");
    String segundoApellido = request.getParameter("segundoApellido") == null ? "" : request
        .getParameter("segundoApellido");
    Genero genero = Genero.valueOf(request.getParameter("genero"));
    int totalAsignaturas = Integer.parseInt(request.getParameter("totalAsignaturas"));
    LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
    BigDecimal becaConcedida = BigDecimal.valueOf(Double.parseDouble(request.getParameter("becaConcedida")));
    int universidades_id = Integer.parseInt(request.getParameter("universidad"));
    LOG.log(Level.INFO, "Nombre: {0}", nombre);
    LOG.log(Level.INFO, "Primer apellido recibido: {0}", primerApellido);
    LOG.log(Level.INFO, "Segundo apellido: {0}", segundoApellido);
    LOG.log(Level.INFO, "Genero: {0}", genero);
    LOG.log(Level.INFO, "Total asignaturas: {0}", totalAsignaturas);
    LOG.log(Level.INFO, "Fecha de nacimiento: {0}", fechaNacimiento);
    LOG.log(Level.INFO, "Beca concedida: {0}", becaConcedida);
    LOG.log(Level.INFO, "Universidad: {0}", universidades_id);
    String correos = request.getParameter("correos");
    String telefonos = request.getParameter("telefonos");
    List<String> direccionesCorreo = null;
    if (correos != null) {
      direccionesCorreo = Arrays.asList(correos.split(SEPARADOR));
      LOG.log(Level.INFO, "Correos: {0}", direccionesCorreo);
    }
    List<String> numerosTelefono = null;
    if (telefonos != null) {
      numerosTelefono = Arrays.asList(telefonos.split(SEPARADOR));
      LOG.log(Level.INFO, "Teléfonos: {0}", numerosTelefono);
    }
    Estudiante estudiante = Estudiante.builder()
      .nombre(nombre)
      .primerApellido(primerApellido)
      .segundoApellido(segundoApellido)
      .genero(genero)
      .totalAsignaturas(totalAsignaturas)
      .fechaNacimiento(fechaNacimiento)
      .becaConcedida(becaConcedida)
      .universidades_id(universidades_id)
      .build();
    LOG.log(Level.INFO, "EMPLEADO: {0}", estudiante);
    EstudianteService estudianteService = new EstudianteServiceImpl();
    
    try {
        estudianteService.altaEstudiante(estudiante, direccionesCorreo, numerosTelefono);
    } catch (SQLException ex) {
        System.getLogger(NuevoEstudianteController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }

    List<Estudiante> estudiantes = estudianteService.getEstudianteList();
    request.setAttribute("estudiantes", estudiantes);
    request.getRequestDispatcher("views/listadoEstudiantes.jsp").forward(request, response);

  }
}
