package com.ejemplo;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // List elementos = new ArrayList();
        // elementos.add("Carolina");
        // elementos.add("Dani");
        // elementos.add(150.5);
        // double elemento = (Double) elementos.get(2);
        // System.out.println(elemento);
        List<Object> lista = new ArrayList<>();
        lista.add("Carolina");
        lista.add("Dani");
        lista.add(150.5);
        lista.add(new Persona());
        for (Object objeto : lista) System.out.println(objeto);
    }
}