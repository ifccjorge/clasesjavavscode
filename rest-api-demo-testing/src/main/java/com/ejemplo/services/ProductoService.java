package com.ejemplo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ejemplo.entities.Producto;

public interface ProductoService {
  Page<Producto> findAll(Pageable pageable);
  List<Producto> findAll(Sort sort);
  Producto findById(int id);
  Producto save(Producto product);
  void delete(Producto product);
  List<Producto> findAll();
}
