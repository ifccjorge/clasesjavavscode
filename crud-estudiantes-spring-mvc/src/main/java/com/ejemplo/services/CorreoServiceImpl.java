package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.CorreoDao;
import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Estudiante;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CorreoServiceImpl implements CorreoService {

  private final CorreoDao correoDao;

    @Override
    public Correo saveCorreo(Correo correo) {
      return correoDao.save(correo);
    }

    @Override
    public List<Correo> getAllCorreos() {
      return correoDao.findAll();
    }

    @Override
    public boolean existsByEstudiante(Estudiante estudiante) {
      return correoDao.existsByEstudiante(estudiante);
    }

    @Override
    public void deleteByEstudiante(Estudiante estudiante) {
      correoDao.deleteByEstudiante(estudiante);
    }

    @Override
    public List<Correo> findByEstudiante(Estudiante estudiante) {
      return correoDao.findByEstudiante(estudiante);
    }

}
