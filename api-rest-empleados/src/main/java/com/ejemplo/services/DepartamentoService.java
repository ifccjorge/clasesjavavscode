package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Departamento;

public interface DepartamentoService {
  List<Departamento> findAll();
  void save(Departamento departamento);
  Departamento findById(int id);
}
