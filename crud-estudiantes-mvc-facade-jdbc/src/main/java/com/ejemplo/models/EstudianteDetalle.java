package com.ejemplo.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder
public record EstudianteDetalle(
  int idEstudiante,
  String nombre,
  String primerApellido,
  String segundoApellido,
  Genero genero,
  int totalAsignaturas,
  LocalDate fechaNacimiento,
  BigDecimal becaConcedida,
  int idUniversidad,
  String nombreUniversidad,
  List<String> telefonos,
  List<String> correos
) {

}
