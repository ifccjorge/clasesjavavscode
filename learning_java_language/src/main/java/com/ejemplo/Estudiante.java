package com.ejemplo;

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
public class Estudiante extends Persona implements Comparable<Estudiante> {
  private NombreFacultad nombreFacultad;
  private int totalAsignaturasMatriculadas;
  private LocalDate fechaAltaFacultad;

    // Orden natural: número total de asignaturas de forma ascendente
    @Override
    public int compareTo(Estudiante e) {
      return Integer.valueOf(this.totalAsignaturasMatriculadas)
        .compareTo(e.getTotalAsignaturasMatriculadas());
    }
}
