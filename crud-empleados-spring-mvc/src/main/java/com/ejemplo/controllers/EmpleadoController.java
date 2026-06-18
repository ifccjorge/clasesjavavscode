package com.ejemplo.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.entities.Empleado;
import com.ejemplo.services.DepartamentoService;
import com.ejemplo.services.EmpleadoService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
  private static final Logger LOG = Logger.getLogger("EmpleadoController");
  private final EmpleadoService empleadoService;
  private final DepartamentoService departamentoService;

  @GetMapping("/listar")
  public String listarEmpleados(Model model) {
    model.addAttribute("empleados", empleadoService.getAllEmpleados());
    return "listadoEmpleados";
  }

  @GetMapping("/alta")
  public String mostrarFormularioAlta(Model model) {
    model.addAttribute("departamentos", departamentoService.getAllDepartamentos());
    model.addAttribute("empleado", new Empleado());
    return "formularioAltaModificacion";
  }

  @PostMapping("/persistir")
  public String procesarFormularioAltaModificacion(
      @ModelAttribute Empleado empleado,
      @RequestParam String telefonosFormulario,
      @RequestParam String correosFormulario
  ) {
    LOG.log(Level.INFO, "Empleado recibido: {0}", empleado);
    LOG.log(Level.INFO, "Teléfonos recibidos: {0}", telefonosFormulario);
    LOG.log(Level.INFO, "Correos recibidos: {0}", correosFormulario);
    
    //empleadoService.saveEmpleado(empleado);
    return "redirect:/empleados/listar";
  }
}
