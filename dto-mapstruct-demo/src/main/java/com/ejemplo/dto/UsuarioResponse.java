package com.ejemplo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponse(long id,
  String name,
  String password,
  String dob
) {

}
