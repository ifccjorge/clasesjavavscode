package com.ejemplo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.entities.Producto;
import com.ejemplo.services.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
  private final ProductoService productoService;
  // Resultado no paginado
  //@GetMapping
  //public List<Producto> getProductos() {
  //  List<Producto> allProductos = productoService.findAll();
  //  return allProductos;
  //}
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
}
