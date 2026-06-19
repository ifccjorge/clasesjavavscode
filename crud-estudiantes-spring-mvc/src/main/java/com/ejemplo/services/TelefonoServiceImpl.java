package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.TelefonoDao;
import com.ejemplo.entities.Estudiante;
import com.ejemplo.entities.Telefono;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TelefonoServiceImpl implements TelefonoService {

  private final TelefonoDao telefonoDao;

    @Override
    public Telefono saveTelefono(Telefono telefono) {
      return telefonoDao.save(telefono);
    }

    @Override
    public List<Telefono> getAllTelefono() {
      return telefonoDao.findAll();
    }

    @Override
    public boolean existsByEstudiante(Estudiante estudiante) {
      return telefonoDao.existsByEstudiante(estudiante);
    }

    @Override
    public void deleteByEstudiante(Estudiante estudiante) {
      telefonoDao.deleteByEstudiante(estudiante);
    }

    @Override
    public List<Telefono> findByEstudiante(Estudiante estudiante) {
      return telefonoDao.findByEstudiante(estudiante);
    }

}
