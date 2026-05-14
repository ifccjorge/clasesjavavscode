package com.ejemplo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {

    // Datos

    Facultad facultad1 = Facultad.builder()
        .nombre("QUÍMICA")
        .build();
    Facultad facultad2 = Facultad.builder()
        .nombre("FILOSOFÍA")
        .build();

    Profesor profesor1 = Profesor.builder()
        .nombre("Luis")
        .primerApellido("Martínez")
        .segundoApellido("López")
        .fechaDeNacimiento(LocalDate.of(1990, Month.AUGUST, 13))
        .totalEstudiantes(10)
        .dpto(Dpto.PROFESORADO)
        .fechaInicioFacultad(LocalDate.of(2024, Month.APRIL, 30))
        .salario(new BigDecimal(20500.25))
        .nombreFacultad(facultad1.getNombre())
        .build();
    Profesor profesor2 = Profesor
        .builder()
        .nombre("Irene")
        .primerApellido("Gómez")
        .segundoApellido("González")
        .fechaDeNacimiento(LocalDate.of(1980, Month.OCTOBER, 2))
        .totalEstudiantes(20)
        .dpto(Dpto.PROFESORADO)
        .fechaInicioFacultad(LocalDate.of(2024, Month.JANUARY, 14))
        .salario(new BigDecimal(20600.75))
        .nombreFacultad(facultad2.getNombre())
        .build();
    Profesor profesor3 = Profesor.builder()
        .nombre("Luis")
        .primerApellido("Martínez")
        .segundoApellido("López")
        .fechaDeNacimiento(LocalDate.of(1990, Month.AUGUST, 13))
        .totalEstudiantes(10)
        .dpto(Dpto.PROFESORADO)
        .fechaInicioFacultad(LocalDate.of(2024, Month.APRIL, 30))
        .salario(new BigDecimal(20500.25))
        .nombreFacultad(facultad1.getNombre())
        .build();
    Profesor profesor4 = Profesor
        .builder()
        .nombre("Irene")
        .primerApellido("Gómez")
        .segundoApellido("González")
        .fechaDeNacimiento(LocalDate.of(1980, Month.OCTOBER, 2))
        .totalEstudiantes(20)
        .dpto(Dpto.PROFESORADO)
        .fechaInicioFacultad(LocalDate.of(2024, Month.OCTOBER, 14))
        .salario(new BigDecimal(20600.75))
        .nombreFacultad(facultad2.getNombre())
        .build();

    Estudiante estudiante1 = Estudiante
        .builder()
        .nombre("Andrés")
        .primerApellido("Díaz")
        .segundoApellido("Montero")
        .fechaDeNacimiento(LocalDate.of(2005, Month.DECEMBER, 2))
        .nombreFacultad(facultad1.getNombre())
        .totalAsignaturasMatriculadas(8)
        .fechaAltaFacultad(LocalDate.of(2025, Month.FEBRUARY, 28))
        .build();
    Estudiante estudiante2 = Estudiante
        .builder()
        .nombre("Patricia")
        .primerApellido("Jiménez")
        .segundoApellido("Pérez")
        .fechaDeNacimiento(LocalDate.of(2006, Month.MAY, 5))
        .totalAsignaturasMatriculadas(6)
        .nombreFacultad(facultad2.getNombre())
        .totalAsignaturasMatriculadas(7)
        .fechaAltaFacultad(LocalDate.of(2025, Month.JUNE, 1))
        .build();
    Estudiante estudiante3 = Estudiante
        .builder()
        .nombre("Andrés")
        .primerApellido("Díaz")
        .segundoApellido("Montero")
        .fechaDeNacimiento(LocalDate.of(2005, Month.DECEMBER, 2))
        .nombreFacultad(facultad1.getNombre())
        .totalAsignaturasMatriculadas(8)
        .fechaAltaFacultad(LocalDate.of(2025, Month.FEBRUARY, 28))
        .build();
    Estudiante estudiante4 = Estudiante
        .builder()
        .nombre("Patricia")
        .primerApellido("Jiménez")
        .segundoApellido("Pérez")
        .fechaDeNacimiento(LocalDate.of(2006, Month.MAY, 5))
        .nombreFacultad(facultad2.getNombre())
        .totalAsignaturasMatriculadas(6)
        .totalAsignaturasMatriculadas(7)
        .fechaAltaFacultad(LocalDate.of(2025, Month.JUNE, 1))
        .build();
    
    // Apartado 1: Crear una lista de facultades, considerando que un estudiante solo se puede matricular en una facultad y que un profesor solo puede trabajar en una facultad.

    List<Facultad> listaFacultades = Arrays.asList(facultad1, facultad2);
    List<Profesor> listaProfesores = Arrays.asList(profesor1, profesor2, profesor3, profesor4);
    List<Estudiante> listaEstudiantes = Arrays.asList(estudiante1, estudiante2, estudiante3, estudiante4);

    List<Profesor> profesoresFacultad1 = listaProfesores.stream()
      .filter(p -> p.getNombreFacultad().equals(facultad1.getNombre())).toList();
    facultad1.setProfesores(profesoresFacultad1);

    List<Estudiante> estudiantesFacultad1 = listaEstudiantes.stream()
      .filter(p -> p.getNombreFacultad().equals(facultad1.getNombre())).toList();
    facultad1.setEstudiantes(estudiantesFacultad1);

    List<Profesor> profesoresFacultad2 = listaProfesores.stream()
      .filter(p -> p.getNombreFacultad().equals(facultad2.getNombre())).toList();
    facultad2.setProfesores(profesoresFacultad2);

    List<Estudiante> estudiantesFacultad2 = listaEstudiantes.stream()
      .filter(p -> p.getNombreFacultad().equals(facultad2.getNombre())).toList();
    facultad2.setEstudiantes(estudiantesFacultad2);

    System.out.println(facultad1);
    System.out.println(facultad2);

    // Apartado 2. Recorrer la lista de facultades y crear una nueva colección que agrupe estudiantes por facultad. 
    //List<String, List<Estudiante>> estudiantesPorFacultad = 

    System.out.println("Apartado 2");
    Map<String, List<Estudiante>> estudiantesPorFacultad = listaFacultades.stream()
        .collect(
            Collectors.toMap(Facultad::getNombre, Facultad::getEstudiantes)  
        );
    System.out.println(estudiantesPorFacultad);

    // Apartado 3. Recorrer la lista de facultades y obtener una nueva colección que agrupe profesores por facultad y Dpto.

    System.out.println("Apartado 3");
    Map<String, Map<Dpto, List<Profesor>>> profesoresPorFacultadYDepartamento = listaFacultades.stream()
        .collect(
            Collectors.groupingBy(
                f -> f.getNombre(),
                Collectors.flatMapping(
                    f -> f.getProfesores().stream(),
                    Collectors.groupingBy(
                        p -> p.getDpto(),
                        Collectors.toList()
                    )
                )
            )
        );
    System.out.println(profesoresPorFacultadYDepartamento);

    // Apartado 4: Recorrer la lista de estudiantes, agrupada por facultad, y mostrar la lista de estudiantes de cada facultad ordenada por el total de asignaturas, según el orden natural.  

    System.out.println("Apartado 4");
    

  }
}
