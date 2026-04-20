package com.ejemplo;

import lombok.Builder;

@Builder
public record Habitante(String nombre, double altura) {
}