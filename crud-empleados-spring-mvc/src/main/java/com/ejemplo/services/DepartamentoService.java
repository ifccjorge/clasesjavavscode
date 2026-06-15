package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Departamento;

public interface DepartamentoService {
  // Definir los metodos que se van a implementar en la clase DepartamentoServiceImpl
  // que implementa esta interfaz

  // Metodo para obtener todos los departamento
  List<Departamento> getAllDepartamentos();

  // Metodo para guardar un departamento
  Departamento saveDepartamento(Departamento departamento);

}
