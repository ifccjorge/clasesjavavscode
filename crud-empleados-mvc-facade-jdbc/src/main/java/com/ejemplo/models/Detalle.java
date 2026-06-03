package com.ejemplo.models;

import java.util.Set;

import lombok.Builder;

@Builder
public record Detalle(
  String nombreDpto,
  Set<String> telefonos,
  Set<String> correos
) {

}
