package com.ejemplo.controller;

import java.io.IOException;
import java.util.List;

import com.ejemplo.models.Estudiante;
import com.ejemplo.service.EstudianteService;
import com.ejemplo.service.EstudianteServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listaestudiantes")
public class ListaEstudiantesController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ListaEstudiantesController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    EstudianteService estudianteService = new EstudianteServiceImpl();
    List<Estudiante> estudiantes = estudianteService.getEstudianteList();
    request.setAttribute("estudiantes", estudiantes);
    request.getRequestDispatcher("views/listadoEstudiantes.jsp").forward(request, response);

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
