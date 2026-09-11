package com.ejemplo.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {
  @NotBlank(message = "El título es oblogatorio")
  private String title;
  private String description;
}