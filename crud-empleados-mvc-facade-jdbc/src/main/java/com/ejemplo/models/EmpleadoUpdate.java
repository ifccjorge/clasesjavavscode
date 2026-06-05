package com.ejemplo.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import lombok.Builder;

@Builder
public record EmpleadoUpdate(
  int idEmp,
  String nombre,
  String primerApellido,
  String segundoApellido,
  LocalDate fechaAlta, 
  Genero genero, 
  BigDecimal salario,
  int idDpto,
  String nombreDpto,
  Set<String> telefonos,
  Set<String> correos
) {

}
