package com.ejemplo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.entities.Empleado;
import com.ejemplo.services.EmpleadoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
  private final EmpleadoService empleadoService;

  // Resultado no paginado: http://localhost:8080/empleados/listado
  @GetMapping("/listado")
  public List<Empleado> getProductos() {
    List<Empleado> allEmpleados = empleadoService.findAll(Sort.by("id"));
    return allEmpleados;
  }

  // Resultado paginado: http://localhost:8080/empleados?page=0&size=3
  @GetMapping
  public ResponseEntity<Map<String, Object>> getProductos(
    @RequestParam(required = false) Integer page,
    @RequestParam(required = false) Integer size
  ) {
    List<Empleado> empleados;
    Map<String, Object> responseMap = new HashMap<>();
    Sort sort = Sort.by("nombre");
    if (page != null && size != null) {
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<Empleado> empleadoPaginados = empleadoService.findAll(pageable);
      empleados = empleadoPaginados.getContent();
    } else {
      empleados = empleadoService.findAll(sort);
    }
    responseMap.put("empleados", empleados);
    return new ResponseEntity<>(responseMap, HttpStatus.OK);
  }

  // Sólo un producto: http://localhost:8080/empleados/1
  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findProductById(
     @PathVariable(name = "id", required = true) int empleado_id
  ) {
    Map<String, Object> responseMap = new HashMap<>();
    ResponseEntity<Map<String, Object>> responseEntity;
    try {
      Empleado empleado = empleadoService.findById(empleado_id);
      if (empleado != null) {
        String successMessage = "El empleado con id " + empleado_id + " ha sido encontrado";
        responseMap.put("mensaje todo OK", successMessage);
        responseMap.put("empleado encontrado", empleado);
        responseEntity = new ResponseEntity<>(responseMap, HttpStatus.OK);
      } else {
        String failureMessage = "No ha sido encotrado el empleado con id " + empleado_id;
        responseMap.put("Error", failureMessage);
        responseEntity = new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
      }
    } catch (DataAccessException e) {
      String failureError = "Error al buscar el empleado id " + empleado_id + " y la causa más probable es "
          + e.getMostSpecificCause().getMessage();
      responseMap.put("Error grave", failureError);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> saveProduct(
    @Valid @RequestBody Empleado empleado,
    BindingResult result
  ) {
    List<String> mensajesError = new ArrayList<>();
    Map<String, Object> responseMap = new HashMap<>();
    ResponseEntity<Map<String, Object>> responseEntity;
    if (result.hasErrors()) {
      List<ObjectError> objectErrors = result.getAllErrors();
      objectErrors.stream().forEach(objectError -> mensajesError.add(objectError.getDefaultMessage()));
      responseMap.put("El empleado tiene los siguientes errores", mensajesError);
      responseMap.put("Empleado mal formado", empleado);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
      return responseEntity;
    }
    try {
      Empleado empleadoPersistido = empleadoService.save(empleado);
      responseMap.put("mensaje", "Empleado persistido exitosamente");
      responseMap.put("Empleado persistido", empleadoPersistido);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    } catch (DataAccessException e) {
      responseMap.put("Error grave", "No ha podido ser guardado el empleado y la causa más probable es "
          + e.getMostSpecificCause().getMessage());
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }
}
