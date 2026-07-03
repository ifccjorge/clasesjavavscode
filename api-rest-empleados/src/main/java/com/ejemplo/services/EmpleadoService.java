package com.ejemplo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ejemplo.entities.Empleado;

public interface EmpleadoService {
  Page<Empleado> findAll(Pageable pageable);
  List<Empleado> findAll(Sort sort);
  Empleado findById(int id);
  Empleado save(Empleado empleado);
  void delete(Empleado empleado);
  List<Empleado> findAll();
}
