package com.ejemplo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "MiPropioBuilderClass")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Vuelo {

  private Destino destino;
  private BigDecimal precio;
  private LocalDate fechaSalida;
  private LocalTime horaSalida;
  private LocalDate fechaLlegada;
  private LocalTime horaLlegada;
  private int numeroPlazas;
  private List<Pasajero> pasajeros;

  public int plazasDisponibles() {
    return this.numeroPlazas - this.pasajeros.size();
  }

  public boolean esDiferenteDia() {
    return fechaSalida.isEqual(fechaSalida);
  }

  public long minutosVuelo() {
    return Duration.between(fechaSalida.atTime(horaSalida), fechaLlegada.atTime(horaLlegada)).toMinutes();
  }

  public void incluirPasajero(Pasajero pasajero) {
    if (this.plazasDisponibles() == 0) {
      System.out.print("No hay plazas para ");
      pasajero.descripcionCorta();
    } else if (this.pasajeros.contains(pasajero)) {
      System.out.print("Ya está en el vuelo ");
      pasajero.descripcionCorta();
    } else {
      pasajeros.add(pasajero);
    }
  }

  public void excluirPasajero(Pasajero pasajero) {
    if (this.pasajeros.contains(pasajero)) {
      pasajeros.remove(pasajero);
    } else {
      System.out.print("No está en el vuelo ");
      pasajero.descripcionCorta();
    }
  }

  public boolean esPasajeroVuelo(Pasajero pasajero) {
    return this.pasajeros.contains(pasajero);
  }

  public void descripcion() {
    System.out.println("Vielo destino " + this.destino + ", salida " + this.fechaSalida + " " + this.horaSalida
            + " y llegada " + this.fechaLlegada + " " + this.horaLlegada + ", con " + this.plazasDisponibles()
            + " plazas libres a " + this.precio + "€");
  }

  public static class MiPropioBuilderClass {

    public Vuelo.MiPropioBuilderClass numeroPlazas(int plazas) {
      this.numeroPlazas = plazas;
      this.pasajeros = new ArrayList<>();
      return this;
    }
  }

}
