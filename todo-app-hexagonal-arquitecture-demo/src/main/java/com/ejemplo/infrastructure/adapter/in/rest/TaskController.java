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
import com.ejemplo.infrastructure.adapter.in.rest.dto.TaskResponse;

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
  public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
    Task task = Task.builder()
        .title(request.getTitle())
        .description(request.getDescription())
        .build();
    Task saved = createTaskUseCase.create(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponse.from(saved));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponse> getById(@PathVariable long id) {
    Task task = getTaskUseCase.getById(id);
    return ResponseEntity.ok(TaskResponse.from(task));
  }

  @GetMapping
  public ResponseEntity<List<TaskResponse>> listAll() {
    List<TaskResponse> response = listTaskUseCase.findAll().stream()
      .map(TaskResponse::from)
      .collect(Collectors.toList());
    return ResponseEntity.ok(response);
  }
}