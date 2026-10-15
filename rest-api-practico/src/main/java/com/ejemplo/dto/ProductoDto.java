package com.ejemplo.dto;

import java.math.BigDecimal;

public record ProductoDto(
  String nombre,
  int existencias,
  BigDecimal precio
) {
}
