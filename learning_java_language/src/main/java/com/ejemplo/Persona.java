package com.ejemplo;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record Persona(
		String nombre,
		String primerApellido,
		String segundoApellido,
		Genero genero,
		LocalDate fechaNacimiento,
		double salario) implements Comparable<Persona>{
    @Override
    public int compareTo(Persona persona) {
		int i1 = this.primerApellido.compareTo(persona.primerApellido);
		int i2 = this.segundoApellido.compareTo(persona.segundoApellido);
		int i3 = this.nombre.compareTo(persona.nombre);
		return i1 != 0 ? i1 : i2 != 0 ? i2 : i3;
    }
}