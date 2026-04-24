package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App {

        public static void main(String[] args) {
                Integer[] arrayNumerosEnteros = { 1, 2, 3, 4, 5 };
                List<Integer> numerosEnteros = Arrays.asList(arrayNumerosEnteros);
                numerosEnteros.forEach(System.out::println);
                List<Persona> personas = new ArrayList<>();
                personas.add(Persona.builder()
                                .nombre("Duglas")
                                .primerApellido("Taydron")
                                .segundoApellido("Gonzalez")
                                .fechaNacimiento(LocalDate.of(1995,
                                                Month.JANUARY, 20))
                                .genero(Genero.HOMBRE)
                                .build());
                personas.add(
                                Persona.builder()
                                                .nombre("Carolina")
                                                .primerApellido("Garzon")
                                                .segundoApellido("Becerra")
                                                .fechaNacimiento(LocalDate.of(2000,
                                                                Month.OCTOBER, 10))
                                                .genero(Genero.MUJER)
                                                .build());

                personas.add(Persona.builder()
                                .nombre("Maria")
                                .primerApellido("Garzon")
                                .segundoApellido("Glez")
                                .fechaNacimiento(LocalDate.of(2005,
                                                Month.DECEMBER, 14))
                                .genero(Genero.MUJER)
                                .build());

                personas.add(Persona.builder()
                                .nombre("Jeronimo")
                                .primerApellido("Arenal")
                                .segundoApellido("Gomez")
                                .fechaNacimiento(LocalDate.of(1989,
                                                Month.MAY, 22))
                                .genero(Genero.HOMBRE)
                                .build());

                // personas.forEach(System.out::println);
                /**
                 * Iterator<Persona> it = personas.iterator();
                 * while (it.hasNext()) {
                 * Persona persona = it.next();
                 * if (persona.getGenero().equals(Genero.MUJER)) {
                 * System.out.println("Se excluye: " + persona);
                 * it.remove();
                 * } else
                 * System.out.println("Se incluye: " + persona);
                 * }
                 * System.out.println("Número: " + personas.size());
                 * System.out.println(personas);
                 */
                // Ejercicio
                Iterator<Persona> it = personas.iterator();
                while (it.hasNext()) {
                        Persona persona = it.next();
                        if (persona.getGenero().equals(Genero.HOMBRE) && persona.getNombre().length() == 6) {
                                System.out.println("Se excluye: " + persona);
                                it.remove();
                        } else
                                System.out.println("Se incluye: " + persona);
                }
                System.out.println("Número: " + personas.size());
                System.out.println(personas);
        }
}