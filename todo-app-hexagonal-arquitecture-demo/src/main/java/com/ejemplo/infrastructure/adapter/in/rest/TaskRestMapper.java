package com.ejemplo.infrastructure.adapter.in.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ejemplo.domain.model.Task;
import com.ejemplo.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.ejemplo.infrastructure.adapter.in.rest.dto.DtoTaskResponse;
import com.ejemplo.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;

@Mapper(componentModel = "spring")
public interface TaskRestMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "completedAt", ignore = true)
  @Mapping(target = "imagePath", ignore = true)
  Task toDomain(CreateTaskRequest createTaskRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "completedAt", ignore = true)
  @Mapping(target = "imagePath", ignore = true)
  Task toDomain(UpdateTaskRequest updateTaskRequest);

  DtoTaskResponse toResponse(Task task);

}
