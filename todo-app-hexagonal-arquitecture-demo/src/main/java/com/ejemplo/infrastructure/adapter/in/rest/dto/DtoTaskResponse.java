package com.ejemplo.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

import com.ejemplo.domain.model.TaskStatus;

public record DtoTaskResponse(
  long id,
  String title,
  String description,
  TaskStatus status,
  LocalDateTime createdAt,
  LocalDateTime completedAt,
  String imagePath) {}
