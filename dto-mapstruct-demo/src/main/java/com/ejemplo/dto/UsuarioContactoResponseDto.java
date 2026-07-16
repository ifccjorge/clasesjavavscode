package com.ejemplo.dto;

import java.util.Set;

import com.ejemplo.entity.Contacto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioContactoResponseDto(
  long idDto,
  String nameDto,
  String passwordDto,
  String dobDto,
  String statusDto,
  Set<Contacto> contactosDto
) {

}
