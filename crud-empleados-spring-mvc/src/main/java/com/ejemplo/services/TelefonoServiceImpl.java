package com.ejemplo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.TelefonoDao;
import com.ejemplo.entities.Empleado;
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
    public boolean existsByEmpleado(Empleado empleado) {
      return telefonoDao.existsByEmpleado(empleado);
    }

    @Override
    public void deleteByEmpleado(Empleado empleado) {
      telefonoDao.deleteByEmpleado(empleado);
    }

    @Override
    public List<Telefono> findByEmpleado(Empleado empleado) {
      return telefonoDao.findByEmpleado(empleado);
    }

}
