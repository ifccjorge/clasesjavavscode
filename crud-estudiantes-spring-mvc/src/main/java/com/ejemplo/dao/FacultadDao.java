package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Facultad;


public interface FacultadDao extends JpaRepository<Facultad, Integer>{
  
}
