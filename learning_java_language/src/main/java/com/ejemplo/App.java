package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        // Definición de lista y set
        List<? super Producto> lista = new ArrayList<>();
        Set<Telefono> telefonos = new HashSet<>();
        Set<Coche> coches = new HashSet<>();

        // Inserta datos en la lista
        lista.add(Telefono.builder().nombre("Samsung Galaxy A16").marca("Samsung").modelo("Galaxy A16").stock(5)
                .build());
        lista.add(Telefono.builder().nombre("Xiaomi REDMI Note 14").marca("Xiaomi").modelo("REDMI Note 14")
                .stock(2).build());
        lista.add(Coche.builder().nombre("Peugeot 208 Hibryd").marca("Peugeot").modelo("208 Hibryd")
                .fechaMatriculacion(LocalDate.of(2024, Month.APRIL, 2)).build());
        lista.add(Coche.builder().nombre("Hyundai i30 Híbrido 48V").marca("Hyundai").modelo("i30 Híbrido 48V")
                .fechaMatriculacion(LocalDate.of(2023, Month.DECEMBER, 12)).build());

        // Recorre todos los productos con for mejorado
        // switch expression con Pattern Matching
        // for (Object producto : lista) {
        //         switch (producto) {
        //                 case Telefono telefono -> {
        //                         System.out.println(telefono);
        //                         telefonos.add(telefono);
        //                 }
        //                 case Coche coche -> {
        //                         System.out.println(coche);
        //                         coches.add(coche);
        //                 }
        //                 default -> System.out.println("Error");
        //         }
        // }
        /* Utilizando operaciones de agregado para recorrer la lista */
        lista.forEach(obj -> {
            switch (obj) {
                case Telefono telefono -> {
                    System.out.println(telefono);
                    telefonos.add(telefono);
                }
                case Coche coche -> {
                    System.out.println(coche);
                    coches.add(coche);
                }
                default ->
                    System.out.println("Error");
            }
        });
        // Muestra los set
        System.out.println(telefonos.size() + " teléfonos registrados:");
        telefonos.forEach(System.out::println);
        System.out.println(coches.size() + " coches registrados:");
        coches.forEach(System.out::println);
    }
}
