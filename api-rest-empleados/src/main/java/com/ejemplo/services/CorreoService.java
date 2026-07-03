package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Correo;

public interface CorreoService {
  List<Correo> findAll();
  void save(Correo correo);
  Correo findById(int id);
}
