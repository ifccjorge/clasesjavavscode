package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Empleado;



public interface EmpleadoDao extends JpaRepository<Empleado, Integer>{
  //List<Empleado> findByNombre(String nombre);
  //boolean existsByGenero(Genero genero);
}
