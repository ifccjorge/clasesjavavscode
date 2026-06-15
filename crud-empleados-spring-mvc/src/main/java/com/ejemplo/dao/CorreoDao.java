package com.ejemplo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Empleado;



public interface CorreoDao extends JpaRepository<Correo, Integer>{

  boolean existsByEmpleado(Empleado empleado);

  void deleteByEmpleado(Empleado empleado);

  List<Correo> findByEmpleado(Empleado empleado);
}
