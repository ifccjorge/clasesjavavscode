package com.ejemplo.service;

import com.ejemplo.entity.Contacto;

public interface ContactoService {
  Contacto getContactoById(long id);
  void save(Contacto contacto);
}
