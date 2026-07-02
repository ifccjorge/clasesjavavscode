package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Profesor;

public interface ProfesorService {

  // Metodo para obtener todos los profesores
  List<Profesor> getAllProfesor();

  // Metodo para obtener un profesor por su id
  Profesor getProfesorById(int id);

  // Metodo para guardar un profesor
  Profesor saveProfesor(Profesor profesor);

  // Metodo para eliminar un profesor por su id
  void deleteProfesor(int id);
  
  // Metodo para eliminar un profesor
  void deleteProfesor(Profesor profesor);
  
  // Metodo para actualizar un profesor
  Profesor updateProfesor(Profesor profesor);
}
