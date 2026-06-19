package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Estudiante;


public interface EstudianteDao extends JpaRepository<Estudiante, Integer>{
  //List<Estudiante> findByNombre(String nombre);
  //boolean existsByGenero(Genero genero);
}
