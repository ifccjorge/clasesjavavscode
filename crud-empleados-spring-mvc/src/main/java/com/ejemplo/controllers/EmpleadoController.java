package com.ejemplo.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;
import com.ejemplo.services.DepartamentoService;
import com.ejemplo.services.EmpleadoService;

import jakarta.validation.Valid;
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
  public String mostrarFormularioAlta(
      Model model,
      @ModelAttribute Empleado empleado
  ) {
    model.addAttribute("departamentos", departamentoService.getAllDepartamentos());
    return "formularioAltaModificacion";
  }

  @PostMapping("/persistir")
  public String procesarFormularioAltaModificacion(
      Model model,
      @Valid
      @ModelAttribute Empleado empleado,
      BindingResult result,
      @RequestParam String telefonosFormulario,
      @RequestParam String correosFormulario
  ) {
    // Comprobación de errores
    if (result.hasErrors()) {
        model.addAttribute("departamentos", departamentoService.getAllDepartamentos());
        return "formularioAltaModificacion";
    }
    // Captura parámetros
    LOG.log(Level.INFO, "Empleado recibido: {0}", empleado);
    LOG.log(Level.INFO, "Teléfonos recibidos: {0}", telefonosFormulario);
    LOG.log(Level.INFO, "Correos recibidos: {0}", correosFormulario);
    // Incluye teléfonos
    Set<Telefono> telefonos = Arrays.stream(telefonosFormulario.split(Empleado.SEPARADOR)).map(
      s -> Telefono.builder().numero(s.trim()).empleado(empleado).build()
    ).collect(HashSet::new, HashSet::add, HashSet::addAll);
    empleado.setTelefonos(telefonos);
    // Incluye correos
    Set<Correo> correos = Arrays.stream(correosFormulario.split(Empleado.SEPARADOR)).map(
      s -> Correo.builder().email(s.trim()).empleado(empleado).build())
    .collect(HashSet::new, HashSet::add, HashSet::addAll);
    empleado.setEmails(correos);
    // Graba empleado
    empleadoService.saveEmpleado(empleado);
    return "redirect:/empleados/listar";
  }
}
