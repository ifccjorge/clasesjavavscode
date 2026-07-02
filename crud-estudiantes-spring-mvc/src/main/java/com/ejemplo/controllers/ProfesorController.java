package com.ejemplo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.entities.Profesor;
import com.ejemplo.services.FacultadService;
import com.ejemplo.services.ProfesorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/profesores")
@RequiredArgsConstructor
public class ProfesorController {
  
  private static final Logger LOG = Logger.getLogger("ProfesorController");
  private final ProfesorService profesorService;
  private final FacultadService facultadService;

  @GetMapping("/listar")
  public String listarProfesores(Model model) {
    model.addAttribute("profesores", profesorService.getAllProfesor());
    return "listadoProfesores";
  }

  @GetMapping("/alta")
  public String mostrarFormularioAlta(
      Model model,
      @ModelAttribute Profesor profesor
  ) {
    model.addAttribute("facultades", facultadService.getAllFacultades());
    return "formularioAltaModificacionProfesores";
  }

  @PostMapping("/persistir")
  public String procesarFormularioAltaModificacion(
      Model model,
      @Valid
      @ModelAttribute Profesor profesor,
      @RequestParam(required = false) MultipartFile file
  ) {
    // Foto
    if (file != null && !file.isEmpty()) {
      Path rutaRelativa = Paths.get("src/main/resources/static/imagenes");
      String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();
      Path rutaCompleta = Paths.get(rutaAbsoluta, file.getOriginalFilename());
      try {
        byte[] imagenRecibidaEnBytes = file.getBytes();
        Files.write(rutaCompleta, imagenRecibidaEnBytes);
        profesor.setFoto(file.getOriginalFilename());
      } catch (IOException e) {
        LOG.log(Level.SEVERE, "Error al guardar la imagen del profesor: {0}", e.getMessage());
        LOG.log(Level.FINE, "Detalle de la excepción al guardar la imagen", e);
      }
    }
    // Captura parámetros
    LOG.log(Level.INFO, "Profesor recibido: {0}", profesor);
    // Graba profesor
    profesorService.saveProfesor(profesor);
    return "redirect:/profesores/listar";
  }

  @GetMapping("/detalles/{id}")
  public String mostrarDetalles(Model model, @PathVariable(name = "id", required = true) int profesor_id) {
    model.addAttribute("profesor", profesorService.getProfesorById(profesor_id));
    return "detallesProfesores";
  }

	@GetMapping("/modificar/{id}")
	public String updateProfesor(Model model, @PathVariable(name = "id", required = true) int idProfesor) {
    Profesor profesor = profesorService.getProfesorById(idProfesor);
    model.addAttribute("profesor", profesor);
    model.addAttribute("facultades", facultadService.getAllFacultades());
    return "formularioAltaModificacionProfesores";
  }
  
  @GetMapping("/eliminar/{idProfesor}")
  public String deleteProfesor(Model model, @PathVariable int idProfesor) {
    Profesor profesorEliminar = profesorService.getProfesorById(idProfesor);
    if (profesorEliminar.getFoto() != null) {
      Path rutaRelativa = Paths.get("src/main/resources/static/imagenes/" + profesorEliminar.getFoto());
      if (Files.exists(rutaRelativa)) {
        try {
          Files.delete(rutaRelativa);
        } catch (IOException e) {
          LOG.log(Level.SEVERE, "Error al eliminar la imagen del profesor: {0}", e.getMessage());
          LOG.log(Level.FINE, "Detalle de la excepción al eliminar la imagen", e);
        }
      }
    }
    profesorService.deleteProfesor(profesorEliminar);
    return "redirect:/profesores/listar";
  }
}
