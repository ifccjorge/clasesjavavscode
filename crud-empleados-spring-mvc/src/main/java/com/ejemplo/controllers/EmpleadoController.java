package com.ejemplo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejemplo.entities.Empleado;
import com.ejemplo.services.DepartamentoService;
import com.ejemplo.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
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
}
