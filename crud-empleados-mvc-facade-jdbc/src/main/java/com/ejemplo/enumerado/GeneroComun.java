package com.ejemplo.enumerado;

import java.util.Random;

public enum GeneroComun {
  MASCULINO, FEMENINO;

  private static final Random PRNG = new Random();

  public static GeneroComun randomDepartamento()  {
    GeneroComun[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }
}
