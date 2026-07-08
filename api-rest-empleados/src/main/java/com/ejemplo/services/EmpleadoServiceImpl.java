package com.ejemplo.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ejemplo.dao.CorreoDao;
import com.ejemplo.dao.EmpleadoDao;
import com.ejemplo.dao.TelefonoDao;
import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

  private final EmpleadoDao empleadoDao;
  private final TelefonoDao telefonoDao;
  private final CorreoDao correoDao;
  
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
    // Obtención de teléfonos y correos del empleado
    Set<Telefono> telefonos = empleado.getTelefonos();
    Set<Correo> correos = empleado.getEmails();
    // Eliminación de teléfonos y correos del empleado
    empleado.setTelefonos(null);
    empleado.setEmails(null);
    // Empleado guardado
    Empleado empleadoPersistido = empleadoDao.save(empleado);
    // Teléfonos guardados
    for (Telefono telefono : telefonos) {
      telefono.setEmpleado(empleadoPersistido);
      telefonoDao.save(telefono);
    }
    // Correos guardados
    for (Correo correo : correos) {
      correo.setEmpleado(empleadoPersistido);
      correoDao.save(correo);
    }
    // Incluye teléfonos y correos en el empleado
    empleadoPersistido.setTelefonos(telefonos);
    empleadoPersistido.setEmails(correos);
    return empleadoPersistido;
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
