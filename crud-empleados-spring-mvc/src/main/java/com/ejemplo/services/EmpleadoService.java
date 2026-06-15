package com.ejemplo.services;

import java.util.List;

import com.ejemplo.entities.Empleado;

public interface EmpleadoService {
  // Definir los metodos que se van a implementar en la clase EmpleadoServiceImpl
  // que implementa esta interfaz

  // Metodo para obtener todos los empleados
  List<Empleado> getAllEmpleados();

  // Metodo para obtener un empleado por su id
  Empleado getEmpleadoById(int id);

  // Metodo para guardar un empleado
  Empleado saveEmpleado(Empleado empleado);

  // Metodo para eliminar un empleado por su id
  void deleteEmpleado(int id);
  
  void deleteEmpleado(Empleado empleado);
  
  Empleado updateEmpleado(Empleado empleado);
}
