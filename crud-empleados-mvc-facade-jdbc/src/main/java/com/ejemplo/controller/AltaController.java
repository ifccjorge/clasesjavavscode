package com.ejemplo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ejemplo.models.Departamento;
import com.ejemplo.models.Empleado;
import com.ejemplo.models.Genero;
import com.ejemplo.service.DepartamentoService;
import com.ejemplo.service.DepartamentoServiceImpl;
import com.ejemplo.service.EmpleadoService;
import com.ejemplo.service.EmpleadoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaController")
public class AltaController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger("AltaController");

  /**
   *  @see HttpServlet#HttpServlet()
   */
  public AltaController() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    DepartamentoService departamentoService = new DepartamentoServiceImpl();
    List<Departamento> departamentos = departamentoService.getDepartamentoList();
    request.setAttribute("departamentos", departamentos);
    request.getRequestDispatcher("views/formularioAltaModificacion.jsp").forward(request, response);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String nombre = request.getParameter("nombre");
    String primerApellido = request.getParameter("primerApellido");
    String segundoApellido = request.getParameter("segundoApellido") == null ? "" : request
        .getParameter("segundoApellido");
    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
    Genero genero = Genero.valueOf(request.getParameter("genero"));
    BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(request.getParameter("salario")));
    int departamentos_id = Integer.parseInt(request.getParameter("departamento"));
    String correos = request.getParameter("correos");
    String telefonos = request.getParameter("telefonos");
    LOG.log(Level.INFO, "Nombre recibido: {0}", nombre);
    LOG.log(Level.INFO, "Primer apellido recibido: {0}", primerApellido);
    LOG.log(Level.INFO, "Segundo apellido: {0}", segundoApellido);
    LOG.log(Level.INFO, "Fecha de alta: {0}", fechaAlta);
    LOG.log(Level.INFO, "Genero: {0}", genero);
    LOG.log(Level.INFO, "Salario: {0}", salario);
    LOG.log(Level.INFO, "Departamento: {0}", departamentos_id);
    List<String> direccionesCorreo = null;
    if (correos != null) {
      direccionesCorreo = Arrays.asList(correos.split(";"));
      LOG.log(Level.INFO, "Correos: {0}", direccionesCorreo);
    }
    List<String> numerosTelefono = null;
    if (telefonos != null) {
      numerosTelefono = Arrays.asList(telefonos.split(";"));
      LOG.log(Level.INFO, "Teléfonos: {0}", numerosTelefono);
    }
    Empleado empleado = Empleado.builder()
      .nombre(nombre)
      .primerApellido(primerApellido)
      .segundoApellido(segundoApellido)
      .fechaAlta(fechaAlta)
      .genero(genero)
      .salario(salario)
      .departamentos_id(departamentos_id)
      .build();
    LOG.log(Level.INFO, "EMPLEADO: {0}", empleado);
    EmpleadoService empleadoService = new EmpleadoServiceImpl();
    
    try {
        empleadoService.altaEmpleado(empleado, direccionesCorreo, numerosTelefono);
    } catch (SQLException ex) {
        System.getLogger(AltaController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }

    List<Empleado> empleados = empleadoService.getEmpleadoList();
    request.setAttribute("empleados", empleados);
    request.getRequestDispatcher("views/listadoEmpleados.jsp").forward(request, response);
  }
}
