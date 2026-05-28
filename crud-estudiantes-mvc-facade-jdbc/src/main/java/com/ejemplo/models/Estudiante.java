package com.ejemplo.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record Estudiante(
  int id,
  String nombre,
  String primerApellido,
  String segundoApellido,
  Genero genero,
  int totalAsignaturas,
  LocalDate fechaNacimiento,
  BigDecimal becaConcedida,
  int universidades_id
) {

}
