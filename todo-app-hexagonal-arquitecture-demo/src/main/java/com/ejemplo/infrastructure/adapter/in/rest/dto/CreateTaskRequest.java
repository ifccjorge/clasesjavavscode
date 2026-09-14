package com.ejemplo.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {

  @NotBlank(message = "El título es obligatorio")
  private String title;

  @NotBlank(message = "La descripción es obligatoria")
  private String description;
}