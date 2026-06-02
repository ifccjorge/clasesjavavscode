package com.ejemplo.models;

import lombok.Builder;

@Builder
public record Universidad(
  int id,
  String nombre
) {

}
