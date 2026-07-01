package com.ejemplo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;
import com.ejemplo.services.CorreoService;
import com.ejemplo.services.DepartamentoService;
import com.ejemplo.services.EmpleadoService;
import com.ejemplo.services.TelefonoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
  
  private static final Logger LOG = Logger.getLogger("EmpleadoController");
  private final EmpleadoService empleadoService;
  private final DepartamentoService departamentoService;
  private final CorreoService correoService;
  private final TelefonoService telefonoService;

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
      @RequestParam String correosFormulario,
      @RequestParam(required = false) MultipartFile file
  ) {
    // Comprobación de errores
    if (result.hasErrors()) {
      model.addAttribute("departamentos", departamentoService.getAllDepartamentos());
      return "formularioAltaModificacion";
    }
    // Foto
    if (file != null && !file.isEmpty()) {
      Path rutaRelativa = Paths.get("src/main/resources/static/imagenes");
      String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();
      Path rutaCompleta = Paths.get(rutaAbsoluta, file.getOriginalFilename());
      try {
        byte[] imagenRecibidaEnBytes = file.getBytes();
        Files.write(rutaCompleta, imagenRecibidaEnBytes);
        empleado.setFoto(file.getOriginalFilename());
      } catch (IOException e) {
        LOG.log(Level.SEVERE, "Error al guardar la imagen del empleado: {0}", e.getMessage());
        LOG.log(Level.FINE, "Detalle de la excepción al guardar la imagen", e);
      }
    }
    // Captura parámetros
    LOG.log(Level.INFO, "Empleado recibido: {0}", empleado);
    LOG.log(Level.INFO, "Teléfonos recibidos: {0}", telefonosFormulario);
    LOG.log(Level.INFO, "Correos recibidos: {0}", correosFormulario);
    // Incluye teléfonos
    if (!telefonosFormulario.isEmpty() && !telefonosFormulario.isBlank()) {
      Set<Telefono> telefonos = Arrays.stream(telefonosFormulario.split(Empleado.SEPARADOR)).map(
          s -> Telefono.builder().numero(s.trim()).empleado(empleado).build())
          .collect(HashSet::new, HashSet::add, HashSet::addAll);
      empleado.setTelefonos(telefonos);
    }
    // Incluye correos
    if (!correosFormulario.isEmpty() && !correosFormulario.isBlank()) {
      Set<Correo> correos = Arrays.stream(correosFormulario.split(Empleado.SEPARADOR)).map(
          s -> Correo.builder().email(s.trim()).empleado(empleado).build())
          .collect(HashSet::new, HashSet::add, HashSet::addAll);
      empleado.setEmails(correos);
    }
    // Elimina teléfonos y correos existentes
    if (empleado.getId() != 0) {
      if (telefonoService.existsByEmpleado(empleado)) telefonoService.deleteByEmpleado(empleado);
      if (correoService.existsByEmpleado(empleado)) correoService.deleteByEmpleado(empleado);
    }
    // Graba empleado
    empleadoService.saveEmpleado(empleado);
    return "redirect:/empleados/listar";
  }

  @GetMapping("/detalles/{id}")
  public String mostrarDetalles(Model model, @PathVariable(name = "id", required = true) int empleado_id) {
    model.addAttribute("empleado", empleadoService.getEmpleadoById(empleado_id));
    return "detalles";
  }

	@GetMapping("/modificar/{id}")
	public String updateEmpleado(Model model, @PathVariable(name = "id", required = true) int idEmpleado) {
    Empleado empleado = empleadoService.getEmpleadoById(idEmpleado);
    model.addAttribute("empleado", empleado);
    model.addAttribute("departamentos", departamentoService.getAllDepartamentos());
    model.addAttribute("tel", empleado.telefonosSeparador());
    model.addAttribute("cor", empleado.correosSeparador());
    Set<Telefono> telefonos = empleado.getTelefonos();
    if (!telefonos.isEmpty()) {
      String numerosTelefono = telefonos.stream()
        .map(Telefono::getNumero)
        .collect(Collectors.joining(Empleado.SEPARADOR));
      model.addAttribute("numerosTelefono", numerosTelefono);
    }
    Set<Correo> correos = empleado.getEmails();
    if (!correos.isEmpty()) {
      String direccionesCorreos = correos.stream()
          .map(correo -> correo.getEmail())
          .collect(Collectors.joining(Empleado.SEPARADOR));
      model.addAttribute("direccionesCorreos", direccionesCorreos);
    }
    return "formularioAltaModificacion";
  }
  
  @GetMapping("/eliminar/{idEmpleado}")
  public String deleteEmpleado(Model model, @PathVariable int idEmpleado) {
    Empleado empleadoEliminar = empleadoService.getEmpleadoById(idEmpleado);
    if (empleadoEliminar.getFoto() != null) {
      Path rutaRelativa = Paths.get("src/main/resources/static/imagenes/" + empleadoEliminar.getFoto());
      if (Files.exists(rutaRelativa)) {
        try {
          Files.delete(rutaRelativa);
        } catch (IOException e) {
          LOG.log(Level.SEVERE, "Error al eliminar la imagen del empleado: {0}", e.getMessage());
          LOG.log(Level.FINE, "Detalle de la excepción al eliminar la imagen", e);
        }
      }
    }
    empleadoService.deleteEmpleado(empleadoEliminar);
    return "redirect:/empleados/listar";
  }
}
