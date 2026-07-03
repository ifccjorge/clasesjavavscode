package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.CorreoDao;
import com.ejemplo.entities.Correo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CorreoServiceImpl implements CorreoService {
  private final CorreoDao correoDao;

    @Override
    public List<Correo> findAll() {
      return correoDao.findAll();
    }

    @Override
    public void save(Correo correo) {
      correoDao.save(correo);
    }

    @Override
    public Correo findById(int id) {
      return correoDao.findById(id).orElseThrow(() -> new RuntimeException("No ha sido encontrada el correo para el id suministrado"));
    }
  
}
