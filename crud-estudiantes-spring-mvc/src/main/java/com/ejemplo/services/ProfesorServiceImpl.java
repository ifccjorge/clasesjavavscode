package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.ProfesorDao;
import com.ejemplo.entities.Profesor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfesorServiceImpl implements ProfesorService {

  private final ProfesorDao profesorDao;

    @Override
    public List<Profesor> getAllProfesor() {
      return profesorDao.findAll();
    }

    @Override
    public Profesor getProfesorById(int id) {
      return profesorDao.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Profesor saveProfesor(Profesor profesor) {
      return profesorDao.save(profesor);
    }

    @Override
    public void deleteProfesor(int id) {
      profesorDao.deleteById(id);
    }

    @Override
    public void deleteProfesor(Profesor profesor) {
      profesorDao.delete(profesor);
    }

    @Override
    public Profesor updateProfesor(Profesor profesor) {
      return profesorDao.save(profesor);
    }

}
