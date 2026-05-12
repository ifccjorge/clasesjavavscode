package com.ejemplo;

import java.time.LocalDate;
import java.time.Period;

public record Pasajero(
    String nombre,
    String primerApellido,
    String segundoApellido,
    LocalDate fechaNacimiento,
    Genero genero) implements Comparable<Pasajero> {

  public Integer edad() {
    return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
  }

  public void descripcionCorta() {
    System.out.println("Pasajero " + this.nombre + " " + this.primerApellido + " " + this.segundoApellido + " de "
        + this.edad() + " años");
  }

  @Override
  public int compareTo(Pasajero p) {
    int i1 = this.nombre.compareTo(p.nombre());
    int i2 = this.primerApellido.compareTo(p.primerApellido());
    int i3 = this.segundoApellido.compareTo(p.segundoApellido());
    return i1 != 0 ? i1 : i2 != 0 ? i2 : i3;
  }
}
