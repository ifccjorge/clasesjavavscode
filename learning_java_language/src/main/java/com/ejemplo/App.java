package com.ejemplo;

public class App {
    // Polimorfimo
    // public class Persona extends Object implements Bicicleta
    Persona persona1 = new Persona(); // referencia al mismo tipo del objeto
    Object objeto = persona1; // referencia a un supertipo (casting implícito)
    @SuppressWarnings("unused")
    Persona persona2 = (Persona) objeto; // referencia a un subtipo (casting explícito)
    Bicicleta bicicleta = persona1; // referencia a interfaz implementada en una clase (casting implícito)
    @SuppressWarnings("unused")
    Persona persona3 = (Persona) bicicleta; // referencia a una clase desde interfaz implementada (casting explícito)
}