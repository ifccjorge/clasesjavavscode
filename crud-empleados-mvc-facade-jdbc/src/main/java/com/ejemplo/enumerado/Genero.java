package com.ejemplo.enumerado;

import java.util.Random;

public enum Genero {
  MASCULINO, FEMENINO;

  private static final Random PRNG = new Random();

  public static Genero randomDepartamento()  {
    Genero[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }
}
