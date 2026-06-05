package com.ejemplo.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.Detalle;
import com.ejemplo.models.Estudiante;
import com.ejemplo.service.EstudianteService;
import com.ejemplo.service.EstudianteServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detalleestudiante")
public class DetalleEstudianteController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("DetallesController");

  /**
   * @see HttpServlet#HttpServlet()
   */
  public DetalleEstudianteController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
    LOG.log(Level.INFO, "Estudiante recibido: {0}", idEstudiante);
    // Capa de servicio
    EstudianteService estudianteService = new EstudianteServiceImpl();
    Detalle detalle = null;
    Estudiante estudiante = null;
    try {
      detalle = estudianteService.getDetalleEstudiante(idEstudiante);
      estudiante = estudianteService.getEstudianteList().stream().filter(e -> e.id() == idEstudiante).toList().getFirst();
    } catch (Exception ex) {
      System.getLogger(DetalleEstudianteController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }

    request.setAttribute("detalle", detalle);
    request.setAttribute("estudiante", estudiante);
    request.getRequestDispatcher("views/detalleEstudiante.jsp").forward(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
