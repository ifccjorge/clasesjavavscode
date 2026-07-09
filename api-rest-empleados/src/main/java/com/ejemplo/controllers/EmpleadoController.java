package com.ejemplo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.entities.Empleado;
import com.ejemplo.models.FileUploadResponse;
import com.ejemplo.services.EmpleadoService;
import com.ejemplo.utilities.FileDownloadUtil;
import com.ejemplo.utilities.FileUploadUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
  
  private final static String ID = "id";
  private final static String NOMBRE = "nombre";
  private final EmpleadoService empleadoService;
  private final FileDownloadUtil fileDownloadUtil;
  private final FileUploadUtil fileUploadUtil;

  // Resultado no paginado: http://localhost:8080/empleados/listado
  @GetMapping("/listado")
  public List<Empleado> getEmpleados() {
    List<Empleado> allEmpleados = empleadoService.findAll(Sort.by(ID));
    return allEmpleados;
  }

  // Resultado paginado: http://localhost:8080/empleados?page=0&size=3
  @GetMapping
  public ResponseEntity<Map<String, Object>> getEmpleados(
    @RequestParam(required = false) Integer page,
    @RequestParam(required = false) Integer size
  ) {
    List<Empleado> empleados;
    Map<String, Object> responseMap = new HashMap<>();
    Sort sort = Sort.by(NOMBRE);
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

  // Sólo un empleado: http://localhost:8080/empleados/1
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

  // Enviar una lista de empleados (application/json)
  @PostMapping(path = "/datos", consumes = "application/json")
  public ResponseEntity<List<Map<String, Object>>> saveProduct(
    @Valid @RequestBody List<Empleado> empleados,
    BindingResult result
  ) {
    List<String> mensajesError = new ArrayList<>();
    Map<String, Object> responseMap;
    List<Map<String, Object>> responseListMap = new ArrayList<>();
    ResponseEntity<List<Map<String, Object>>> responseEntity;
    if (result.hasErrors()) {
      List<ObjectError> objectErrors = result.getAllErrors();
      for (ObjectError objectError : objectErrors) mensajesError.add(objectError.getDefaultMessage());
      responseMap = new HashMap<>();
      responseMap.put("Los empleados tienen los siguientes errores", mensajesError);
      responseMap.put("Empleados mal formados", empleados);
      responseListMap.add(responseMap);
      responseEntity = new ResponseEntity<>(responseListMap, HttpStatus.BAD_REQUEST);
      return responseEntity;
    }
    try {
      for (Empleado empleado : empleados) {
        Empleado empleadoPersistido = empleadoService.save(empleado);
        responseMap = new HashMap<>();
        responseMap.put("mensaje", "Empleado persistido exitosamente");
        responseMap.put("Empleado persistido", empleadoPersistido);
        responseListMap.add(responseMap);
      }
      responseEntity = new ResponseEntity<>(responseListMap, HttpStatus.CREATED);
    } catch (DataAccessException e) {
      responseMap = new HashMap<>();
      responseMap.put("Error grave", "No ha podido ser guardada la lista de empleados y la causa más probable es "
          + e.getMostSpecificCause().getMessage());
      responseListMap.add(responseMap);
      responseEntity = new ResponseEntity<>(responseListMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

  // Enviar empleado (application/json) con imagen (application/octet-stream)
  @PostMapping(consumes = "multipart/form-data")
  @Transactional
  public ResponseEntity<Map<String, Object>> saveProduct(
    @Valid @RequestPart Empleado empleado,
    BindingResult result,
    @RequestPart(name = "file", required = false) MultipartFile imagenDelEmpleado
  ) throws IOException {
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
    if (imagenDelEmpleado != null && !imagenDelEmpleado.isEmpty()) {
      String fileCode = fileUploadUtil.saveFile(imagenDelEmpleado.getOriginalFilename(), imagenDelEmpleado);
      empleado.setImagenEmpleado(fileCode + "-" + imagenDelEmpleado.getOriginalFilename());
      FileUploadResponse fileUploadResponse = new FileUploadResponse(
          fileCode + "-" + imagenDelEmpleado.getOriginalFilename(),
          "/empleados/fileDownload/" + fileCode,
          imagenDelEmpleado.getSize());
      responseMap.put("Información de la imagen del empleado", fileUploadResponse);
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

  // Descargar imagen: http://localhost:8080/empleados/fileDownload/hrUfluSx
  @GetMapping("/fileDownload/{fileCode}")
  public ResponseEntity<?> downloadFile(@PathVariable String fileCode) {
    Resource resource;
    try {
      resource = fileDownloadUtil.getFileResource(fileCode);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    if (resource == null) {
      return new ResponseEntity<>("Imagen del empleado no encontrada", HttpStatus.NOT_FOUND);
    }
    String contentType = "application/octet-stream";
    String headerValue = "attachment; fileName=\"" + resource.getFilename() + "\"";
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
      .body(resource);
  }
}
