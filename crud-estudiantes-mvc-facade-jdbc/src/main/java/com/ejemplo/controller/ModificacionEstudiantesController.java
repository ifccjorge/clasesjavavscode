package com.ejemplo.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.EstudianteDetalle;
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

@WebServlet("/modificacionestudiantes")
public class ModificacionEstudiantesController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("ModificacionEstudiantesController");

  /**
   *  @see HttpServlet#HttpServlet()
   */
  public ModificacionEstudiantesController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
    LOG.log(Level.INFO, "Estudiante recibido: {0}", idEstudiante);
    // Capa de servicio
    EstudianteService estudianteService = new EstudianteServiceImpl();
    UniversidadService universidadService = new UniversidadServiceImpl();
    List<Universidad> universidades = null;
    EstudianteDetalle estudianteDetalle = null;
    try {
      estudianteDetalle = estudianteService.getEstudianteId(idEstudiante);
      universidades = universidadService.getUniversidadList();
    } catch (Exception ex) {
      System.getLogger(ModificacionEstudiantesController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    request.setAttribute("estudianteDetalle", estudianteDetalle);
    request.setAttribute("universidades", universidades);
    request.getRequestDispatcher("views/formularioAltaEstudiantes.jsp").forward(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        doGet(request, response);
  }
}
