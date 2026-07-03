package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.TelefonoDao;
import com.ejemplo.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService {
  private final TelefonoDao telefonoDao;

    @Override
    public List<Telefono> findAll() {
      return telefonoDao.findAll();
    }

    @Override
    public void save(Telefono telefono) {
      telefonoDao.save(telefono);
    }

    @Override
    public Telefono findById(int id) {
      return telefonoDao.findById(id).orElseThrow(() -> new RuntimeException("No ha sido encontrada el telefono para el id suministrado"));
    }
  
}
