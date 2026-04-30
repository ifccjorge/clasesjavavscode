package com.ejemplo;

public class Persona extends Object implements Bicicleta {
        @Override
        public void acelerar(double velocidad) {
                System.err.println(velocidad);
        }
}