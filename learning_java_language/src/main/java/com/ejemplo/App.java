package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

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
                                .salario(1000)
                                .build());
                personas.add(
                                Persona.builder()
                                                .nombre("Carolina")
                                                .primerApellido("Garzon")
                                                .segundoApellido("Becerra")
                                                .fechaNacimiento(LocalDate.of(2000,
                                                                Month.OCTOBER, 10))
                                                .genero(Genero.MUJER)
                                                .salario(1200)
                                                .build());

                personas.add(Persona.builder()
                                .nombre("Maria")
                                .primerApellido("Garzon")
                                .segundoApellido("Glez")
                                .fechaNacimiento(LocalDate.of(2005,
                                                Month.DECEMBER, 14))
                                .genero(Genero.MUJER)
                                .salario(1400)
                                .build());

                personas.add(Persona.builder()
                                .nombre("Jeronimo")
                                .primerApellido("Arenal")
                                .segundoApellido("Gomez")
                                .fechaNacimiento(LocalDate.of(1989,
                                                Month.MAY, 22))
                                .genero(Genero.HOMBRE)
                                .salario(1600)
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
                        if (persona.genero().equals(Genero.HOMBRE) && persona.nombre().length() == 6) {
                                System.out.println("Se excluye: " + persona);
                                it.remove();
                        } else
                                System.out.println("Se incluye: " + persona);
                }
                System.out.println("Número: " + personas.size());
                System.out.println(personas);
                // Traversing collection con funciones de agregado
                Predicate<Persona> predicadoGenero1 = new Filtro();
                @SuppressWarnings("Convert2Lambda")
                Predicate<Persona> predicadoGenero2 = new Predicate<Persona>() {
                        @Override
                        public boolean test(Persona p) {
                                return p.genero().equals(Genero.MUJER);
                        }
                };
                @SuppressWarnings("Convert2Lambda")
                Consumer<Persona> cnsmr = new Consumer<Persona>() {
                        @Override
                        public void accept(Persona persona) {
                                System.out.println("*** " + persona + " ***");
                        }
                };
                @SuppressWarnings("Convert2Lambda")
                Function<Persona, Persona> fnctn = new Function<Persona, Persona>() {
                        @Override
                        public Persona apply(Persona persona) {
                                return Persona.builder()
                                                .nombre(persona.nombre())
                                                .primerApellido(persona.primerApellido())
                                                .segundoApellido(
                                                                persona.segundoApellido())
                                                .fechaNacimiento(persona.fechaNacimiento())
                                                .genero(persona.genero())
                                                .salario(persona.salario() * 2)
                                                .build();
                        }
                };
                @SuppressWarnings("Convert2Lambda")
                ToDoubleFunction<Persona> tdf = new ToDoubleFunction<Persona>() {
                        @Override
                        public double applyAsDouble(Persona persona) {
                                return persona.salario();
                        }
                };
                // Clase externa
                personas.stream().filter(predicadoGenero1).map(fnctn).forEach(cnsmr);
                // Clase anónima
                System.out.println(personas.stream().filter(predicadoGenero2).mapToDouble(tdf).average().getAsDouble());
                // Expresión lambda
                System.out.println(personas.stream().filter(p -> p.genero()
                                .equals(Genero.MUJER)).mapToDouble(p -> p.salario()).average()
                                .orElse(0));
                // Método por referencia
                System.out.println(personas.stream().filter(p -> p.genero()
                                .equals(Genero.MUJER)).mapToDouble(Persona::salario).average()
                                .orElse(0));
                // Ordenamiento
                List<String> listaInmutable = List.of("Jerónimo", "Duglas", "Carolina");
                List<String> sortedList1 = listaInmutable.stream().sorted().collect(Collectors.toList());
                System.out.println(sortedList1);
                List<String> sortedList2 = listaInmutable.stream().sorted().toList();
                System.out.println(sortedList2);
                List<String> listaMutable = Arrays.asList("Jerónimo", "Duglas", "Carolina");
                Collections.sort(listaMutable);
                System.out.println(listaMutable);
        }
}