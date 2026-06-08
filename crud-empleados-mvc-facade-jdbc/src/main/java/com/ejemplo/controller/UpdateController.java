package com.ejemplo.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.Departamento;
import com.ejemplo.models.EmpleadoUpdate;
import com.ejemplo.service.DepartamentoService;
import com.ejemplo.service.DepartamentoServiceImpl;
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
    DepartamentoService departamentoService = new DepartamentoServiceImpl();
    EmpleadoService empleadoService = new EmpleadoServiceImpl();
    List<Departamento> departamentos = null;
    EmpleadoUpdate empleadoUpdate = null;
    try {
      empleadoUpdate = empleadoService.getEmpleadosById(idEmpleado);
      departamentos = departamentoService.getDepartamentoList();
      LOG.log(Level.INFO, "Empleado actualizar: {0}", empleadoUpdate);
    } catch (Exception ex) {
      System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    request.setAttribute("departamentos", departamentos);
    request.setAttribute("empleadoUpdate", empleadoUpdate);
    request.getRequestDispatcher("views/formularioAltaModificacion.jsp").forward(request, response);

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
