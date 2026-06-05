package com.ejemplo.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.EmpleadoUpdate;
import com.ejemplo.service.EmpleadoService;
import com.ejemplo.service.EmpleadoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("DetallesController");

  /**
   *  @see HttpServlet#HttpServlet()
   */
  public UpdateController() {
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
    EmpleadoUpdate empleadoUpdate = null;
    try {
      empleadoUpdate = empleadoService.getEmpleadosById(idEmpleado);
      //empleado = empleadoService.getEmpleadoList().stream().filter(e -> e.id() == idEmpleado).toList().getFirst();
    } catch (Exception ex) {
        System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }

    request.setAttribute("empleadoUpdate", empleadoUpdate);
    //request.getRequestDispatcher("views/detalleEmpleado.jsp").forward(request, response);

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
