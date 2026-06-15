package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.EmpleadoDao;
import com.ejemplo.entities.Empleado;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

  private final EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> getAllEmpleados() {
      return empleadoDao.findAll();
    }

    @Override
    public Empleado getEmpleadoById(int id) {
      return empleadoDao.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
      return empleadoDao.save(empleado);
    }

    @Override
    public void deleteEmpleado(int id) {
      empleadoDao.deleteById(id);
    }

    @Override
    public void deleteEmpleado(Empleado empleado) {
      empleadoDao.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Empleado empleado) {
      return empleadoDao.save(empleado);
    }

}
