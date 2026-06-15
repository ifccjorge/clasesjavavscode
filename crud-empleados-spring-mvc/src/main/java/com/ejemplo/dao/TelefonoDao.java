package com.ejemplo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;



public interface TelefonoDao extends JpaRepository<Telefono, Integer> {

  boolean existsByEmpleado(Empleado empleado);

  void deleteByEmpleado(Empleado empleado);

  List<Telefono> findByEmpleado(Empleado empleado);
}
