package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.DepartamentoDao;
import com.ejemplo.entities.Departamento;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DepartamentoServiceImpl implements DepartamentoService {
  
  private final DepartamentoDao departamentoDao;

  @Override
  public List<Departamento> getAllDepartamentos() {
    return departamentoDao.findAll();
  }

  @Override
  public Departamento saveDepartamento(Departamento departamento) {
    return departamentoDao.save(departamento);
  }

}
