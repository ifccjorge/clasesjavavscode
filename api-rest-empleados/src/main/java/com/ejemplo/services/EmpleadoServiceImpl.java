package com.ejemplo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejemplo.dao.EmpleadoDao;
import com.ejemplo.entities.Empleado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoDao empleadoDao;
    
    @Override
    public Page<Empleado> findAll(Pageable pageable) {
      return empleadoDao.findAll(pageable);
    }

    @Override
    public List<Empleado> findAll(Sort sort) {
      return empleadoDao.findAll(sort);
    }

    @Override
    public Empleado findById(int id) {
      return empleadoDao.findById(id).get();
    }

    @Override
    public Empleado save(Empleado empleado) {
      return empleadoDao.save(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
      empleadoDao.delete(empleado);
    }

    @Override
    public List<Empleado> findAll() {
      return empleadoDao.findAll();
    }

}
