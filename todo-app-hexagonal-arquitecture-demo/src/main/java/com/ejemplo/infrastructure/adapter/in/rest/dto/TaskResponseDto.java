package com.ejemplo.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

import com.ejemplo.domain.model.Task;
import com.ejemplo.domain.model.TaskStatus;

//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;

public record TaskResponseDto(
  long idDto,
  String titleDto,
  String descriptionDto,
  TaskStatus statusDto,
  LocalDateTime createdAtDto,
  LocalDateTime completedAtDto)
{
  public static TaskResponseDto from(Task task) {
    return new TaskResponseDto(
      task.getId(),
      task.getTitle(),
      task.getDescription(),
      task.getStatus(),
      task.getCreatedAt(),
      task.getCompletedAt()
    );
  }

}