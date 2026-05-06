package com.ejemplo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<String> listadoDeArgumentos = Arrays.asList(args);
        listadoDeArgumentos.forEach(System.out::println);
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        Map<String, Integer> m3 = new HashMap<>();
        for (String nombre : listadoDeArgumentos) {
            if (m1.containsKey(nombre)) {
                m1.put(nombre, m1.get(nombre) + 1);
            } else {
                m1.put(nombre, 1);
            }
        }
        System.out.println(m1);
        listadoDeArgumentos.forEach(s -> {
            Integer i = m2.get(s);
            m2.put(s, i == null ? 1 : i + 1);
        });
        System.out.println(m2);
        listadoDeArgumentos.forEach(s -> m3.put(s, m3.getOrDefault(s, 0) + 1));
        System.out.println(m3);
        Map<String, Long> m4 = listadoDeArgumentos.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(m4);
        Map<String, Long> m5 = listadoDeArgumentos.stream().collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.counting()));
        System.out.println(m5);
    }
}
