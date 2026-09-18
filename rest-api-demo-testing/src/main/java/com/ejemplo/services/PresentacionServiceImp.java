package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.PresentacionDao;
import com.ejemplo.entities.Presentacion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresentacionServiceImp implements PresentacionService {
  private final PresentacionDao presentacionDao;

    @Override
    public List<Presentacion> findAll() {
      return presentacionDao.findAll();
    }

    @Override
    public void save(Presentacion presentacion) {
      presentacionDao.save(presentacion);
    }

    @Override
    public Presentacion findById(int id) {
      return presentacionDao.findById(id).orElseThrow(() -> new RuntimeException("No ha sido encontrada la presentacion para el id suministrado"));
    }
  
}
