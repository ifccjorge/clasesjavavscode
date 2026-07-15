package com.ejemplo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponseDto(
  long idDto,
  String nameDto,
  String passwordDto,
  String dobDto,
  String statusDto,
  String mobDto,
  String emailIdDto
) {

}
