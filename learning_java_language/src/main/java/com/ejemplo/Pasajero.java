package com.ejemplo;

import java.time.LocalDate;
import java.time.Period;

public record Pasajero(
    String nombre,
    String primerApellido,
    String segundoApellido,
    LocalDate fechaNacimiento,
    Genero genero) {

  public Integer edad() {
    return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
  }

  public void descripcionCorta() {
    System.out.println("Pasajero " + this.nombre + " " + this.primerApellido + " " + this.segundoApellido + " de "
        + this.edad() + " años");
  }
}
