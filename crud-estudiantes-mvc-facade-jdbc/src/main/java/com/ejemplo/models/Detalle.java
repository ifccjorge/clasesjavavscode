package com.ejemplo.models;

import java.util.List;

import lombok.Builder;

@Builder
public record Detalle(
  String nombreUniversidad,
  List<String> telefonos,
  List<String> correos
) {

}
