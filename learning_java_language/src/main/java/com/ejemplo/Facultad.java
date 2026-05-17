package com.ejemplo;

import java.util.List;

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
@EqualsAndHashCode
@ToString
public class Facultad {
  private NombreFacultad nombre;
  private List<Profesor> profesores;
  private List<Estudiante> estudiantes;
}
