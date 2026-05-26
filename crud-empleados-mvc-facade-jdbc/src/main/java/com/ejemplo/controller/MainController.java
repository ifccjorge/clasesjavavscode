package com.ejemplo.controller;

import java.io.IOException;
import java.util.logging.Logger;

import com.ejemplo.service.EmpleadoService;
import com.ejemplo.service.EmpleadoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("MainController");

  /**
   *  @see HttpServlet#HttpServlet()
   */
  public MainController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //response.getWriter().append("Served at: ").append(request.getContextPath());
    EmpleadoService empleadoService = new EmpleadoServiceImpl();
    boolean conectionResult = false;
    try {
        conectionResult = empleadoService.isConnectionOK();
      } catch (Exception ex) {
          System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
    if (conectionResult)
      LOG.info("Conexión exitosa");
    else
      LOG.info("Error de conexión a la base de datos");
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
