package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Presentacion;

public interface PresentacionService {
  List<Presentacion> findAll();
  void save(Presentacion presentacion);
  Presentacion findById(int id);
}
