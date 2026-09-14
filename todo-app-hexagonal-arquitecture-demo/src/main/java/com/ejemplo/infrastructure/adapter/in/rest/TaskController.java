package com.ejemplo.infrastructure.adapter.in.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.application.port.in.CreateTaskUseCase;
import com.ejemplo.application.port.in.GetTaskUseCase;
import com.ejemplo.application.port.in.ListTaskUseCase;
import com.ejemplo.domain.model.Task;
import com.ejemplo.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.ejemplo.infrastructure.adapter.in.rest.dto.TaskResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
  private final CreateTaskUseCase createTaskUseCase;
  private final GetTaskUseCase getTaskUseCase;
  private final ListTaskUseCase listTaskUseCase;
  @PostMapping
  public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody CreateTaskRequest request) {
    Task task = Task.builder()
        .title(request.getTitle())
        .description(request.getDescription())
        .build();
    Task saved = createTaskUseCase.create(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponseDto.from(saved));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDto> getById(@PathVariable long id) {
    Task task = getTaskUseCase.getById(id);
    return ResponseEntity.ok(TaskResponseDto.from(task));
  }

  @GetMapping
  public ResponseEntity<List<TaskResponseDto>> listAll() {
    List<TaskResponseDto> response = listTaskUseCase.findAll().stream()
      .map(TaskResponseDto::from)
      .collect(Collectors.toList());
    return ResponseEntity.ok(response);
  }
}