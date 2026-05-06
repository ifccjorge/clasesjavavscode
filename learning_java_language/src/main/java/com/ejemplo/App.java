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

        Empleado emp1 = Empleado.builder()
                .nombre("Jorge Francisco")
                .primerApellido("Alborch")
                .segundoApellido("Villar")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1973, Month.JUNE, 23))
                .dpto(Dpto.INFORMATICA)
                .salario(new BigDecimal(3500.50))
                .fechaAlta(LocalDate.of(1990, Month.SEPTEMBER, 22))
                .build();

        Empleado emp2 = Empleado.builder()
                .nombre("Andres")
                .primerApellido("Alonso")
                .segundoApellido("Pelaez")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1983, Month.SEPTEMBER, 23))
                .dpto(Dpto.INFORMATICA)
                .salario(new BigDecimal(3400.50))
                .fechaAlta(LocalDate.of(1995, Month.SEPTEMBER, 22))
                .build();

        Empleado emp3 = Empleado.builder()
                .nombre("Jeronimo")
                .primerApellido("Arenal")
                .segundoApellido("Gomez")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1968, Month.OCTOBER, 20))
                .dpto(Dpto.CONTABILIDAD)
                .salario(new BigDecimal(3600.50))
                .fechaAlta(LocalDate.of(1977, Month.JANUARY, 4))
                .build();

        Empleado emp4 = Empleado.builder()
                .nombre("Carolina")
                .primerApellido("Garzon")
                .segundoApellido("Becerra")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(2001, Month.JUNE, 7))
                .dpto(Dpto.INFORMATICA)
                .salario(new BigDecimal(3700.50))
                .fechaAlta(LocalDate.of(2020, Month.SEPTEMBER, 10))
                .build();

        Empleado emp5 = Empleado.builder()
                .nombre("Mariana")
                .primerApellido("Garzon")
                .segundoApellido("Villar")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(2000, Month.AUGUST, 4))
                .dpto(Dpto.FINANZAS)
                .salario(new BigDecimal(3300.50))
                .fechaAlta(LocalDate.of(2022, Month.SEPTEMBER, 25))
                .build();

        Empleado emp6 = Empleado.builder()
                .nombre("Francisca")
                .primerApellido("Alvarez")
                .segundoApellido("Glez")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(1995, Month.DECEMBER, 10))
                .dpto(Dpto.RRHH)
                .salario(new BigDecimal(2500.50))
                .fechaAlta(LocalDate.of(2010, Month.SEPTEMBER, 5))
                .build();

        Empleado emp7 = Empleado.builder()
                .nombre("Maricarmen")
                .primerApellido("Becerra")
                .segundoApellido("Mtnez")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(2003, Month.FEBRUARY, 14))
                .dpto(Dpto.FINANZAS)
                .salario(new BigDecimal(2600.50))
                .fechaAlta(LocalDate.of(2021, Month.SEPTEMBER, 8))
                .build();

        Empleado emp8 = Empleado.builder()
                .nombre("Eva")
                .primerApellido("Cornide")
                .segundoApellido("Machado")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(1990, Month.MAY, 18))
                .dpto(Dpto.INFORMATICA)
                .salario(new BigDecimal(3500.50))
                .fechaAlta(LocalDate.of(2015, Month.SEPTEMBER, 22))
                .build();

        Empleado emp9 = Empleado.builder()
                .nombre("Alberto")
                .primerApellido("Glez")
                .segundoApellido("Sanchez")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1990, Month.FEBRUARY, 14))
                .dpto(Dpto.FINANZAS)
                .salario(new BigDecimal(2600.50))
                .fechaAlta(LocalDate.of(2008, Month.SEPTEMBER, 8))
                .build();

        Empleado emp10 = Empleado.builder()
                .nombre("Javier")
                .primerApellido("Glez")
                .segundoApellido("Sanchez")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1995, Month.MAY, 20))
                .dpto(Dpto.RRHH)
                .salario(new BigDecimal(3500.50))
                .fechaAlta(LocalDate.of(2015, Month.SEPTEMBER, 22))
                .build();

        List<Empleado> empleados = Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10);
        Map<Genero, List<Empleado>> empleadosPorGenero = empleados.stream()
                .collect(Collectors.groupingBy(Empleado::getGenero, Collectors.toList()));
        System.out.println(empleadosPorGenero);
        /*
        List<? super Persona> empleados2 = Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10);
        Map<Genero, List<Empleado>> empleadosPorGenero2 = empleados2.stream()
                .filter(obj -> obj instanceof Empleado)
                .map(obj -> (Empleado) obj)
                .collect(Collectors.groupingBy(Empleado::getGenero, Collectors.toList()));
         */

    }
}
