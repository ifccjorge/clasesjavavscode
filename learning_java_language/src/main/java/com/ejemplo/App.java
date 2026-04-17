package com.ejemplo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class App {
    public static void main(String[] args) {
        // EJERCICIO
        // Empleados
        Empleado empleado1 = Empleado.builder()
                .nombre("Luis Alberto")
                .primerApellido("Díaz")
                .segundoApellido("Gómez")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1999, Month.DECEMBER, 23))
                .salario(10500.50)
                .dpto(Dpto.FINANAZAS)
                .fechaAlta(LocalDate.of(2024, Month.SEPTEMBER, 3))
                .ssn("9748964301")
                .build();
        Empleado empleado2 = Empleado.builder()
                .nombre("José Luis")
                .primerApellido("Fernández")
                .segundoApellido("Alonso")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(1994, Month.DECEMBER, 1))
                .salario(11500.20)
                .dpto(Dpto.FINANAZAS)
                .fechaAlta(LocalDate.of(2025, Month.MAY, 30))
                .ssn("5673340867")
                .build();
        Empleado empleado3 = Empleado.builder()
                .nombre("María Luisa")
                .primerApellido("del Pino")
                .segundoApellido("Martínez")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(1997, Month.NOVEMBER, 10))
                .salario(13500.80)
                .dpto(Dpto.FINANAZAS)
                .fechaAlta(LocalDate.of(2023, Month.OCTOBER, 30))
                .ssn("9000875439")
                .build();
        // Estudiantes
        Estudiante estudiante1 = Estudiante.builder()
                .nombre("Pedro")
                .primerApellido("González")
                .segundoApellido("de Miguel")
                .genero(Genero.HOMBRE)
                .fechaNacimiento(LocalDate.of(2007, Month.SEPTEMBER, 13))
                .totalAsignaturas(5)
                .facultad(Facultad.INGENIERIA)
                .build();
        Estudiante estudiante2 = Estudiante.builder()
                .nombre("Luisa")
                .primerApellido("Giménez")
                .segundoApellido("Gallardo")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(2008, Month.JUNE, 1))
                .totalAsignaturas(4)
                .facultad(Facultad.LETRAS)
                .build();
        Estudiante estudiante3 = Estudiante.builder()
                .nombre("Irene")
                .primerApellido("Rodríguez")
                .segundoApellido("Pérez")
                .genero(Genero.MUJER)
                .fechaNacimiento(LocalDate.of(2008, Month.AUGUST, 15))
                .totalAsignaturas(9)
                .facultad(Facultad.BIOLOGIA)
                .build();
        // Definición del array
        Object[] array = { empleado1, empleado2, empleado3, estudiante1, estudiante2, estudiante3 };
        // Variable para el cálculo de medias
        int total_empleados_hombre = 0;
        double suma_salarios_hombre = 0;
        int total_estudiantes = 0;
        double suma_asignaturas = 0;
        // For mejorado
        for (Object objeto : array) {
            if (objeto instanceof Empleado empleado && empleado.getGenero().equals(Genero.HOMBRE)) {
                // Salario medio en hombres
                total_empleados_hombre++;
                suma_salarios_hombre += empleado.getSalario();
            } else if (objeto instanceof Estudiante estudiante) {
                // Número medio asignaturas
                total_estudiantes++;
                suma_asignaturas += estudiante.getTotalAsignaturas();
            }
        }
        // Muestra de resultados
        System.out.println("EJERCICIO");
        System.out.println("Salario medio en hombres: " + suma_salarios_hombre / total_empleados_hombre);
        System.out.println("Número medio asignaturas: " + suma_asignaturas / total_estudiantes);
        // Ejemplo switch
        System.out.println("EJEMPLO con switch");
        DayOfWeek today = DayOfWeek.FRIDAY;
        switch (today) {
            case MONDAY:
                System.out.println("Vaya! Se hacen eternos los lunes");
                break;
            case TUESDAY:
                System.out.println("Es martes");
                break;
            case FRIDAY:
                System.out.println("Es viernes y el cuerpo lo sabe");
                break;
            default:
                System.out.println("No existe ninguna info para el dia introducido");
                break;
        }
    }
}