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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.entities.Producto;
import com.ejemplo.models.FileUploadResponse;
import com.ejemplo.services.ProductoService;
import com.ejemplo.utilities.FileDownloadUtil;
import com.ejemplo.utilities.FileUploadUtil;
import com.ejemplo.utilities.FileUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

  private final ProductoService productoService;
  private final FileUploadUtil fileUploadUtil;
  private final FileDownloadUtil fileDownloadUtil;
  private final FileUtil fileUtil;

  // Resultado no paginado: http://localhost:8080/productos/listado
  @GetMapping("/listado")
  public List<Producto> getProductos() {
    List<Producto> allProductos = productoService.findAll(Sort.by("id"));
    return allProductos;
  }

  // Resultado paginado: http://localhost:8080/productos?page=0&size=3
  @GetMapping
  public ResponseEntity<Map<String, Object>> getProductos(
    @RequestParam(required = false) Integer page,
    @RequestParam(required = false) Integer size
  ) {
    List<Producto> productos;
    Map<String, Object> responseMap = new HashMap<>();
    Sort sort = Sort.by("nombre");
    if (page != null && size != null) {
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<Producto> productosPaginados = productoService.findAll(pageable);
      productos = productosPaginados.getContent();
    } else {
      productos = productoService.findAll(sort);
    }
    responseMap.put("productos", productos);
    return new ResponseEntity<>(responseMap, HttpStatus.OK);
  }

  // Sólo un producto: http://localhost:8080/productos/1
  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findProductById(
     @PathVariable(name = "id", required = true) int product_id
  ) {
    Map<String, Object> responseMap = new HashMap<>();
    ResponseEntity<Map<String, Object>> responseEntity;
    try {
      Producto producto = productoService.findById(product_id);
      if (producto != null) {
        String successMessage = "El product con id " + product_id + " ha sido encontrado";
        responseMap.put("mensaje todo OK", successMessage);
        responseMap.put("producto encontrado", producto);
        responseEntity = new ResponseEntity<>(responseMap, HttpStatus.OK);
      } else {
        String failureMessage = "No ha sido encotrado el producto con id " + product_id;
        responseMap.put("Error", failureMessage);
        responseEntity = new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
      }
    } catch (DataAccessException e) {
      String failureError = "Error al buscar el producto id " + product_id + " y la causa más probable es "
          + e.getMostSpecificCause().getMessage();
      responseMap.put("Error grave", failureError);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

  @PostMapping(consumes = "multipart/form-data")
  @Transactional
  public ResponseEntity<Map<String, Object>> saveProduct(
    @Valid @RequestPart Producto producto,
    BindingResult result,
    @RequestPart(name = "file", required = false) MultipartFile imagenDelProducto
  ) throws IOException {
    List<String> mensajesError = new ArrayList<>();
    Map<String, Object> responseMap = new HashMap<>();
    ResponseEntity<Map<String, Object>> responseEntity;
    if (result.hasErrors()) {
      List<ObjectError> objectErrors = result.getAllErrors();
      objectErrors.stream().forEach(objectError -> mensajesError.add(objectError.getDefaultMessage()));
      responseMap.put("El producto tiene los siguientes errores", mensajesError);
      responseMap.put("Producto mal formado", producto);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
      return responseEntity;
    }
    if (imagenDelProducto != null && !imagenDelProducto.isEmpty()) {
      String fileCode = fileUploadUtil.saveFile(imagenDelProducto.getOriginalFilename(), imagenDelProducto);
      producto.setProductoImage(fileCode + "-" + imagenDelProducto.getOriginalFilename());
      FileUploadResponse fileUploadResponse = new FileUploadResponse(
          fileCode + "-" + imagenDelProducto.getOriginalFilename(),
          "/productos/fileDownload/" + fileCode,
          imagenDelProducto.getSize());
      responseMap.put("Información de la imagen del producto", fileUploadResponse);
    }
    try {
      Producto productoPersistido = productoService.save(producto);
      responseMap.put("mensaje", "Producto persistido exitosamente");
      responseMap.put("Producto persistido", productoPersistido);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    } catch (DataAccessException e) {
      responseMap.put("Error grave", "No ha podido ser guardado el producto y la causa más probable es "
          + e.getMostSpecificCause().getMessage());
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

  // Descargar imagen: http://localhost:8080/productos/fileDownload/hrUfluSx
  @GetMapping("/fileDownload/{fileCode}")
  public ResponseEntity<?> downloadFile(@PathVariable String fileCode) {
    Resource resource;
    try {
      resource = fileDownloadUtil.getFileResource(fileCode);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    if (resource == null) {
      return new ResponseEntity<>("Imagen del producto no encontrada", HttpStatus.NOT_FOUND);
    }
    String contentType = "application/octet-stream";
    String headerValue = "attachment; fileName=\"" + resource.getFilename() + "\"";
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
        .body(resource);
  }

  // Actualizar un empleado
  @PutMapping(value = "/{id}", consumes = "multipart/form-data")
  @Transactional
  public ResponseEntity<Map<String, Object>> updateProduct(
      @Valid @RequestPart Producto producto,
      BindingResult result,
      @RequestPart(name = "file", required = false) MultipartFile imagenDelProducto,
      @PathVariable int id
  ) throws IOException {
    List<String> mensajesError = new ArrayList<>();
    Map<String, Object> responseMap = new HashMap<>();
    ResponseEntity<Map<String, Object>> responseEntity;
    if (result.hasErrors()) {
      List<ObjectError> objectErrors = result.getAllErrors();
      objectErrors.stream().forEach(objectError -> mensajesError.add(objectError.getDefaultMessage()));
      responseMap.put("El producto tiene los siguientes errores", mensajesError);
      responseMap.put("Producto mal formado", producto);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
      return responseEntity;
    }
    Producto productoGuardado = productoService.findById(id);
    if (productoGuardado == null) {
      responseMap.put("mensaje de error", "producto con id " + id + " no encontrado");
      return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
    }
    if (imagenDelProducto != null && !imagenDelProducto.isEmpty()) {
      if (productoGuardado.getProductoImage() != null) {
        fileUtil.eliminarArchivo(productoGuardado.getProductoImage());
      }
      String fileCode = fileUploadUtil.saveFile(imagenDelProducto.getOriginalFilename(), imagenDelProducto);
      producto.setProductoImage(fileCode + "-" + imagenDelProducto.getOriginalFilename());
      FileUploadResponse fileUploadResponse = new FileUploadResponse(
          fileCode + "-" + imagenDelProducto.getOriginalFilename(),
          "/productos/fileDownload/" + fileCode,
          imagenDelProducto.getSize());
      responseMap.put("Información de la imagen del producto", fileUploadResponse);
    }
    try {
      producto.setId(id);
      Producto productoPersistido = productoService.save(producto);
      responseMap.put("mensaje", "Producto actualizado exitosamente");
      responseMap.put("Producto actualizado", productoPersistido);
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    } catch (DataAccessException e) {
      responseMap.put("Error grave", "No ha podido ser actualizado el producto y la causa más probable es "
          + e.getMostSpecificCause().getMessage());
      responseEntity = new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
  }

}
