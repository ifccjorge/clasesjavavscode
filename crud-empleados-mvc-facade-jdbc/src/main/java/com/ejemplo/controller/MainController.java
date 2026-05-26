package com.ejemplo.controller;

import java.io.IOException;

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
    empleadoService.isConnectionOK();
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
