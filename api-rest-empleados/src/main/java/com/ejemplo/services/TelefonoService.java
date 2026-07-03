package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Telefono;

public interface TelefonoService {
  List<Telefono> findAll();
  void save(Telefono telefono);
  Telefono findById(int id);
}
