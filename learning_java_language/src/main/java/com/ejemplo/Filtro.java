package com.ejemplo;

import java.util.function.Predicate;

public class Filtro implements Predicate<Persona> {

        @Override
        public boolean test(Persona persona) {
                return persona.genero().equals(Genero.MUJER);
        }

}