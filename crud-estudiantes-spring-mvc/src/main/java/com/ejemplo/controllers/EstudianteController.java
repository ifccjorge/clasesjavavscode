package com.ejemplo.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Estudiante;
import com.ejemplo.entities.Telefono;
import com.ejemplo.services.EstudianteService;
import com.ejemplo.services.FacultadService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {
  
  private static final Logger LOG = Logger.getLogger("EstudianteController");
  private final EstudianteService estudianteService;
  private final FacultadService facultadService;

  @GetMapping("/listar")
  public String listarEstudiantes(Model model) {
    model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
    return "listadoEstudiantes";
  }

  @GetMapping("/alta")
  public String mostrarFormularioAlta(
      Model model,
      @ModelAttribute Estudiante estudiante
  ) {
    model.addAttribute("facultades", facultadService.getAllFacultades());
    return "formularioAltaModificacionEstudiantes";
  }

  @PostMapping("/persistir")
  public String procesarFormularioAltaModificacion(
      @ModelAttribute Estudiante estudiante,
      @RequestParam String telefonosFormulario,
      @RequestParam String correosFormulario
  ) {
    // Captura parámetros
    LOG.log(Level.INFO, "Estudiante recibido: {0}", estudiante);
    LOG.log(Level.INFO, "Teléfonos recibidos: {0}", telefonosFormulario);
    LOG.log(Level.INFO, "Correos recibidos: {0}", correosFormulario);
    // Incluye teléfonos
    Set<Telefono> telefonos = Arrays.stream(telefonosFormulario.split(Estudiante.SEPARADOR)).map(
      s -> Telefono.builder().numero(s.trim()).estudiante(estudiante).build()
    ).collect(HashSet::new, HashSet::add, HashSet::addAll);
    estudiante.setTelefonos(telefonos);
    // Incluye correos
    Set<Correo> correos = Arrays.stream(correosFormulario.split(Estudiante.SEPARADOR)).map(
      s -> Correo.builder().email(s.trim()).estudiante(estudiante).build())
    .collect(HashSet::new, HashSet::add, HashSet::addAll);
    estudiante.setEmails(correos);
    // Graba estudiante
    estudianteService.saveEstudiante(estudiante);
    return "redirect:/estudiantes/listar";
  }
}
