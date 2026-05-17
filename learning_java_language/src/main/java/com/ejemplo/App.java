package com.ejemplo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.function.Function.identity;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class App {

  public static void main(String[] args) {

    // Datos de profesores
    // Se incluye la facultad a la que pertence en el atributo nombreFacultad
    // de este modo se garantiza que un profesor sólo pertenece a una facultad

    Profesor profesor1 = Profesor.builder()
      .nombre("Luis")
      .primerApellido("Martínez")
      .segundoApellido("López")
      .fechaDeNacimiento(LocalDate.of(1990, Month.AUGUST, 13))
      .totalEstudiantes(10)
      .dpto(Dpto.DOCENCIA)
      .fechaInicioFacultad(LocalDate.of(2024, Month.APRIL, 30))
      .salario(new BigDecimal(20500.25))
      .nombreFacultad(NombreFacultad.QUIMICA)
      .build();

    Profesor profesor2 = Profesor.builder()
      .nombre("Irene")
      .primerApellido("Gómez")
      .segundoApellido("González")
      .fechaDeNacimiento(LocalDate.of(1980, Month.OCTOBER, 2))
      .totalEstudiantes(20)
      .dpto(Dpto.INVESTIGACION)
      .fechaInicioFacultad(LocalDate.of(2024, Month.JANUARY, 14))
      .salario(new BigDecimal(20600.75))
      .nombreFacultad(NombreFacultad.QUIMICA)
      .build();

    Profesor profesor3 = Profesor.builder()
      .nombre("Antonio")
      .primerApellido("Blanco")
      .segundoApellido("Gutiérrez")
      .fechaDeNacimiento(LocalDate.of(1987, Month.SEPTEMBER, 9))
      .totalEstudiantes(15)
      .dpto(Dpto.PRACTICAS)
      .fechaInicioFacultad(LocalDate.of(2025, Month.JULY, 31))
      .salario(new BigDecimal(20400.50))
      .nombreFacultad(NombreFacultad.FILOSOFIA)
      .build();

    Profesor profesor4 = Profesor.builder()
      .nombre("Cristina")
      .primerApellido("Cortés")
      .segundoApellido("Alonso")
      .fechaDeNacimiento(LocalDate.of(1980, Month.OCTOBER, 2))
      .totalEstudiantes(16)
      .dpto(Dpto.DOCENCIA)
      .fechaInicioFacultad(LocalDate.of(2024, Month.OCTOBER, 14))
      .salario(new BigDecimal(20500.75))
      .nombreFacultad(NombreFacultad.FILOSOFIA)
      .build();

    Profesor profesor5 = Profesor.builder()
      .nombre("Pedro")
      .primerApellido("Rubio")
      .segundoApellido("Santos")
      .fechaDeNacimiento(LocalDate.of(1987, Month.DECEMBER, 8))
      .totalEstudiantes(11)
      .dpto(Dpto.INVESTIGACION)
      .fechaInicioFacultad(LocalDate.of(2026, Month.MAY, 30))
      .salario(new BigDecimal(20600.25))
      .nombreFacultad(NombreFacultad.ECONOMIA)
      .build();

    Profesor profesor6 = Profesor.builder()
      .nombre("Virginia")
      .primerApellido("Sanz")
      .segundoApellido("Núñez")
      .fechaDeNacimiento(LocalDate.of(1979, Month.FEBRUARY, 16))
      .totalEstudiantes(21)
      .dpto(Dpto.PRACTICAS)
      .fechaInicioFacultad(LocalDate.of(2026, Month.JANUARY, 29))
      .salario(new BigDecimal(20750.25))
      .nombreFacultad(NombreFacultad.ECONOMIA)
      .build();

    // Datos de estudiantes
    // Se incluye la facultad a la que pertence en el atributo nombreFacultad
    // de este modo se garantiza que un estudiante sólo pertenece a una facultad

    Estudiante estudiante1 = Estudiante.builder()
      .nombre("Andrés")
      .primerApellido("Díaz")
      .segundoApellido("Garrido")
      .fechaDeNacimiento(LocalDate.of(2005, Month.DECEMBER, 2))
      .nombreFacultad(NombreFacultad.QUIMICA)
      .totalAsignaturasMatriculadas(8)
      .fechaAltaFacultad(LocalDate.of(2025, Month.FEBRUARY, 28))
      .build();

    Estudiante estudiante2 = Estudiante.builder()
      .nombre("Ana")
      .primerApellido("Jiménez")
      .segundoApellido("Pérez")
      .fechaDeNacimiento(LocalDate.of(2006, Month.MAY, 5))
      .totalAsignaturasMatriculadas(6)
      .nombreFacultad(NombreFacultad.QUIMICA)
      .totalAsignaturasMatriculadas(7)
      .fechaAltaFacultad(LocalDate.of(2025, Month.JUNE, 1))
      .build();

    Estudiante estudiante3 = Estudiante.builder()
      .nombre("Pablo")
      .primerApellido("Molina")
      .segundoApellido("Vázquez")
      .fechaDeNacimiento(LocalDate.of(2006, Month.NOVEMBER, 27))
      .nombreFacultad(NombreFacultad.FILOSOFIA)
      .totalAsignaturasMatriculadas(8)
      .fechaAltaFacultad(LocalDate.of(2025, Month.FEBRUARY, 19))
      .build();

    Estudiante estudiante4 = Estudiante.builder()
      .nombre("María")
      .primerApellido("Torres")
      .segundoApellido("Romero")
      .fechaDeNacimiento(LocalDate.of(2004, Month.MARCH, 30))
      .nombreFacultad(NombreFacultad.FILOSOFIA)
      .totalAsignaturasMatriculadas(6)
      .fechaAltaFacultad(LocalDate.of(2025, Month.OCTOBER, 10))
      .build();

    Estudiante estudiante5 = Estudiante.builder()
      .nombre("Santiago")
      .primerApellido("Serrano")
      .segundoApellido("Suárez")
      .fechaDeNacimiento(LocalDate.of(2004, Month.NOVEMBER, 25))
      .nombreFacultad(NombreFacultad.ECONOMIA)
      .totalAsignaturasMatriculadas(5)
      .fechaAltaFacultad(LocalDate.of(2023, Month.JUNE, 2))
      .build();

    Estudiante estudiante6 = Estudiante.builder()
      .nombre("Julia")
      .primerApellido("Ortíz")
      .segundoApellido("Cano")
      .fechaDeNacimiento(LocalDate.of(2003, Month.MAY, 7))
      .nombreFacultad(NombreFacultad.ECONOMIA)
      .totalAsignaturasMatriculadas(5)
      .fechaAltaFacultad(LocalDate.of(2024, Month.JUNE, 21))
      .build();

    // Apartado 1: Crear una lista de facultades, considerando que un estudiante solo se puede matricular en una facultad y que un profesor solo puede trabajar en una facultad.

    // Lista que incluye todos los profesores
    List<Profesor> listaProfesores = Arrays.asList(profesor1, profesor2, profesor3, profesor4, profesor5, profesor6);
    // Lista que incluye todos los estudiantes
    List<Estudiante> listaEstudiantes = Arrays.asList(estudiante1, estudiante2, estudiante3, estudiante4, estudiante5,
        estudiante6);

    // Lista de facultades sin datos
    List<Facultad> listaFacultades = new ArrayList<>();

    // Carga de listado de facultades a partir de las listas de profesores y estudiantes
    // Se invoca el procedimiento cargaDatosFacultades para cada nombre de facultad
    cargaDatosFacultades(listaFacultades, listaProfesores, listaEstudiantes, NombreFacultad.QUIMICA);
    cargaDatosFacultades(listaFacultades, listaProfesores, listaEstudiantes, NombreFacultad.FILOSOFIA);
    cargaDatosFacultades(listaFacultades, listaProfesores, listaEstudiantes, NombreFacultad.ECONOMIA);


    // Apartado 2. Recorrer la lista de facultades y crear una nueva colección que agrupe estudiantes por facultad. 
    System.out.println("*** APARTADO 2 ***");

    Map<Facultad, List<Estudiante>> estudiantesPorFacultad = listaFacultades.stream()
      .collect(
        toMap(identity(), Facultad::getEstudiantes)
      );

    // Comprobación
    estudiantesPorFacultad.entrySet().forEach(
      entry -> {
        System.out.println("Facultad: " + entry.getKey().getNombre().name());
        entry.getValue().forEach(System.out::println);
      }
    );


    // Apartado 3. Recorrer la lista de facultades y obtener una nueva colección que agrupe profesores por facultad y Dpto.
    System.out.println("*** APARTADO 3 ***");

    Map<Facultad, Map<Dpto, List<Profesor>>> profesoresPorFacultadYDepartamento = listaFacultades.stream()
      .collect(
        groupingBy(
          Function.identity(),
          flatMapping(
            f -> f.getProfesores().stream(),
            groupingBy(
              p -> p.getDpto(),
              toList()
            )
          )
        )
      );

    // Comprobación
    profesoresPorFacultadYDepartamento.entrySet().forEach(
      entry1 -> {
        entry1.getValue().entrySet().forEach(
          entry2 -> {
            System.out.println("Facultad: " + entry1.getKey().getNombre().name()
              + " - Departamento: " + entry2.getKey());
            entry2.getValue().forEach(System.out::println);
          }
        );
      }
    );


    // Apartado 4: Recorrer la lista de estudiantes, agrupada por facultad, y mostrar la lista de estudiantes de cada facultad ordenada por el total de asignaturas, según el orden natural.  
    System.out.println("*** APARTADO 4 ***");

    // Se implementa Comparable en la clase Estudiante para definir el orden natural
    estudiantesPorFacultad.entrySet().forEach(
      entry -> {
        System.out.println("Facultad: " + entry.getKey().getNombre().name());
        entry.getValue().stream().sorted().forEach(System.out::println);
      }
    );
    

    // Apartado 5: Recorrer la colección que agrupa profesores por facultad y Dpto y mostrar la lista de profesores de cada facultad ordenada por salario y antigüedad del profesor, según el orden natural.
    System.out.println("*** APARTADO 5 ***");

    // Se implementa Comparable en la clase Profesor para definir el orden natural
    profesoresPorFacultadYDepartamento.entrySet().forEach(
      entry1 -> {
        entry1.getValue().entrySet().forEach(
          entry2 -> {
            System.out.println("Facultad: " + entry1.getKey().getNombre().name()
              + " - Departamento: " + entry2.getKey());
            entry2.getValue().stream().sorted().forEach(System.out::println);
          }
        );
      }
    );
    

    // Apartado 6: Mostrar el nombre y los apellidos del profesor que tiene mayor salario de todas las facultades.
    System.out.println("*** APARTADO 6 ***");

    listaFacultades.stream()
      .flatMap(f -> f.getProfesores().stream())
      .max(Comparator.comparing(Profesor::getSalario))
      .ifPresent(
        p -> System.out.println("Profesor: " + p.getNombre()
              + " " + p.getPrimerApellido()
              + " " + p.getSegundoApellido()
            )
    );
    

    // Apartado 7: Obtener una colección que agrupe estudiantes por total de asignaturas matriculadas.
    System.out.println("*** APARTADO 7 ***");

    Map<Integer, List<Estudiante>> estudiantesPorTotalAsignaturas = listaFacultades.stream()
      .flatMap(f -> f.getEstudiantes().stream())
      .collect(groupingBy(Estudiante::getTotalAsignaturasMatriculadas));

    // Comprobación
    System.out.println(estudiantesPorTotalAsignaturas);

    
    // Apartado 8: Crear una colección que permita almacenar estudiantes y profesores en la misma colección.
    System.out.println("*** APARTADO 8 ***");

    List<Persona> listaProfesoresEstudiantes = new ArrayList<>();
    listaProfesoresEstudiantes.addAll(listaProfesores);
    listaProfesoresEstudiantes.addAll(listaEstudiantes);

    // Comprobación
    System.out.println(listaProfesoresEstudiantes);

    
    // Apartado 9: Recorrer la colección creada en el punto anterior y mostrar solamente los profesores que tengan salario superior a la media y hayan comenzado a trabajar en la facultad en los últimos 5 días del mes en curso.
    System.out.println("*** APARTADO 9 ***");

    // Fechas del rango que incluyen los últimos 5 días del mes en curso
    LocalDate ultimoDiaMesActual = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    LocalDate fechaAnteriorDias = ultimoDiaMesActual.minusDays(5);

    // Lista de profesores a partir de lista de personas
    List<Profesor> listProfesoresPersona = listaProfesoresEstudiantes.stream()
      .filter(p -> p instanceof Profesor)
      .map(p -> (Profesor) p)
      .toList();

    // Salario medio de los profesores
    BigDecimal mediaSalarios = BigDecimal.valueOf(
      listProfesoresPersona.stream()
        .map(Profesor::getSalario)
        .collect(averagingDouble(BigDecimal::doubleValue))
    );

    // Se recorre la colección aplicando las dos condiciones:
    // se encuentra en el rando de los 5 días finales
    // es mayor que la media de salarios (método compareTo igual a 1)
    listProfesoresPersona.stream()
      .filter(
        p -> (p.getFechaInicioFacultad().isAfter(fechaAnteriorDias)
              && p.getFechaInicioFacultad().isBefore(ultimoDiaMesActual)
              || p.getFechaInicioFacultad().isEqual(ultimoDiaMesActual)
            ) && p.getSalario().compareTo(mediaSalarios) == 1)
      .forEach(System.out::println);

    System.out.println("Media de salarios: " + mediaSalarios);

    
    // Apartado 10: Recorrer la lista de facultades y obtener una nueva colección que agrupe por el total de asignaturas matriculadas por facultad.
    System.out.println("*** APARTADO 10 ***");

    Map<Facultad, Integer> asignaturasMatriculadasFacultad = listaFacultades.stream().collect(
      groupingBy(
        identity(),
        flatMapping(
          f -> f.getEstudiantes().stream(),
          Collectors.summingInt(Estudiante::getTotalAsignaturasMatriculadas)
        )
      )
    );

    // Comprobación
    asignaturasMatriculadasFacultad.entrySet().forEach(
      entry -> System.out.println(entry.getKey().getNombre().name() + ":" + entry.getValue())
    );

  }
  
  
  // Añade una nueva facultad a listaFacultades con su correspondientes listas de profesores y estudiantes
  private static void cargaDatosFacultades(
      List<Facultad> listaFacultades,
      List<Profesor> listaProfesores,
      List<Estudiante> listaEstudiantes,
      NombreFacultad nombreFacultad) {

    // Lista de profesores que pertencen a la facultad
    List<Profesor> profesoresFacultad = listaProfesores.stream()
      .filter(p -> p.getNombreFacultad().equals(nombreFacultad)).toList();
    
    // Lista de estudiantes que pertencen a la facultad
    List<Estudiante> estudiantesFacultad = listaEstudiantes.stream()
      .filter(p -> p.getNombreFacultad().equals(nombreFacultad)).toList();
    
    // Nueva facultad
    Facultad facultad = Facultad.builder()
      .nombre(nombreFacultad)
      .profesores(profesoresFacultad)
      .estudiantes(estudiantesFacultad)
      .build();
    
    // Se añade la facultad a la lista
    listaFacultades.add(facultad);
  }
  
}
