package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Departamento;

public interface DepartamentoDao extends JpaRepository<Departamento, Integer> {

}
