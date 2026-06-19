package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Estudiante;

public interface EstudianteService {
  // Definir los metodos que se van a implementar en la clase EstudianteServiceImpl
  // que implementa esta interfaz

  // Metodo para obtener todos los estudiantes
  List<Estudiante> getAllEstudiantes();

  // Metodo para obtener un estudiante por su id
  Estudiante getEstudianteById(int id);

  // Metodo para guardar un estudiante
  Estudiante saveEstudiante(Estudiante estudiante);

  // Metodo para eliminar un estudiante por su id
  void deleteEstudiante(int id);
  
  void deleteEstudiante(Estudiante estudiante);
  
  Estudiante updateEstudiante(Estudiante estudiante);
}
