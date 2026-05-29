package com.ejemplo.enumerado;

import java.util.Random;

public enum DepartamentoComun {
  RRHH, INFORMATICA, FINANZAS, CONTABILIDAD;

  private static final Random PRNG = new Random();

  public static DepartamentoComun randomDepartamento() {
    DepartamentoComun[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }
}
