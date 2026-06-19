package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.FacultadDao;
import com.ejemplo.entities.Facultad;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FacultadServiceImpl implements FacultadService {
  
  private final FacultadDao facultadDao;

  @Override
  public List<Facultad> getAllFacultades() {
    return facultadDao.findAll();
  }

  @Override
  public Facultad saveFacultad(Facultad facultad) {
    return facultadDao.save(facultad);
  }

}
