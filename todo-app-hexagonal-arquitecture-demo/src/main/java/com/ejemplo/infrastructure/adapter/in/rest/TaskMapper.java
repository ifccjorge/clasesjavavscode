package com.ejemplo.infrastructure.adapter.in.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ejemplo.domain.model.Task;
import com.ejemplo.infrastructure.adapter.in.rest.dto.TaskResponseDto;

@Mapper(componentModel = "spring")
public interface TaskMapper {
  @Mapping(source = "task.id", target = "idDto")
  @Mapping(source = "task.title", target = "titleDto")
  @Mapping(source = "task.description", target = "descriptionDto")
  @Mapping(source = "task.status", target = "statusDto")
  @Mapping(source = "task.createdAt", target = "createdAtDto")
  @Mapping(source = "task.completedAt", target = "completedAtDto")
  TaskResponseDto mapTaskResponseDto(Task task);

}
