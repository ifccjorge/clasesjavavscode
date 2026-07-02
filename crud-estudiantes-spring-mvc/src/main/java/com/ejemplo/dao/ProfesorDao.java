package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Profesor;


public interface ProfesorDao extends JpaRepository<Profesor, Integer>{
  
}
