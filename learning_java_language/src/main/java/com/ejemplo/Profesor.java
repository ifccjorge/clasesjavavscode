package com.ejemplo;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Profesor extends Persona implements Comparable<Profesor> {
  private int totalEstudiantes;
  private Dpto dpto;
  private LocalDate fechaInicioFacultad;
  private BigDecimal salario;
  // Nuevo atributo
  private NombreFacultad nombreFacultad;
  
  // Orden natural: salario y antigüedad del profesor de forma ascendente
  @Override
  public int compareTo(Profesor p) {
    int comparacionSalario = this.salario.compareTo(p.getSalario());
    int comparacionAntiguedad = this.fechaInicioFacultad.compareTo(p.getFechaInicioFacultad());
    return comparacionSalario != 0 ? comparacionSalario : comparacionAntiguedad;
  }
}
