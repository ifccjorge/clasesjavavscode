package com.ejemplo.models;

import lombok.Builder;

@Builder
public record Departamento(
  int id,
  String nombre
) {

}
