package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.DepartamentoDao;
import com.ejemplo.entities.Departamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {
  private final DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> findAll() {
      return departamentoDao.findAll();
    }

    @Override
    public void save(Departamento departamento) {
      departamentoDao.save(departamento);
    }

    @Override
    public Departamento findById(int id) {
      return departamentoDao.findById(id).orElseThrow(() -> new RuntimeException("No ha sido encontrada el departamento para el id suministrado"));
    }
  
}
