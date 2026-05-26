package com.ejemplo;

import java.util.Random;

public enum Departamento {
  RRHH, INFORMATICA, FINANZAS, CONTABILIDAD;

  private static final Random PRNG = new Random();

  public static Departamento randomDepartamento()  {
    Departamento[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }
}
