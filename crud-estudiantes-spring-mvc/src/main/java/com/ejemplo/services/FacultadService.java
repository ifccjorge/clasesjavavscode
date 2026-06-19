package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Facultad;

public interface FacultadService {
  // Definir los metodos que se van a implementar en la clase FacultadServiceImpl
  // que implementa esta interfaz

  // Metodo para obtener todos los facultad
  List<Facultad> getAllFacultades();

  // Metodo para guardar un facultad
  Facultad saveFacultad(Facultad facultad);

}
