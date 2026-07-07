package com.ejemplo.models;

import lombok.Getter;

@Getter
public enum Genero {
  HOMBRE("Hombre"),
  MUJER("Mujer"),
  OTRO("-");
  private final String genero;
  private Genero(String genero) {
    this.genero = genero;
  }
}
