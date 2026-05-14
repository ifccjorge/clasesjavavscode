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
public class Profesor extends Persona {
  private int totalEstudiantes;
  private Dpto dpto;
  private LocalDate fechaInicioFacultad;
  private BigDecimal salario;
  private String nombreFacultad;
}
