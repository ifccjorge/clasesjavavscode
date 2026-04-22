package com.ejemplo;

import lombok.Builder;

@Builder
public record Book(String titulo, String autor) {

}