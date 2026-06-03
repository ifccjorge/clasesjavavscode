package com.ejemplo.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.Detalle;
import com.ejemplo.service.EmpleadoService;
import com.ejemplo.service.EmpleadoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DetallesController")
public class DetallesController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("DetallesController");

  /**
   *  @see HttpServlet#HttpServlet()
   */
  public DetallesController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
    LOG.log(Level.INFO, "Empleado recibido: {0}", idEmpleado);
    // Capa de servicio
    EmpleadoService empleadoService = new EmpleadoServiceImpl();
    Detalle detalle = null;
    try {
        detalle = empleadoService.getDetalles(idEmpleado);
    } catch (Exception ex) {
        System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    //if (conectionResult)
    //  LOG.info("Conexión exitosa");
    //else
    //  LOG.info("Error de conexión a la base de datos");
    request.setAttribute("detalle", detalle);
    request.getRequestDispatcher("views/detalleEmpleado.jsp").forward(request, response);

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
