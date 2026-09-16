package com.ejemplo.infrastructure.adapter.in.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejemplo.application.port.in.CreateTaskUseCase;
import com.ejemplo.application.port.in.DeleteTaskUseCase;
import com.ejemplo.application.port.in.GetTaskUseCase;
import com.ejemplo.application.port.in.ListTaskUseCase;
import com.ejemplo.application.port.in.UpdateTaskUseCase;
import com.ejemplo.application.port.in.UploadTaskImageUseCase;
import com.ejemplo.domain.model.Task;
import com.ejemplo.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.ejemplo.infrastructure.adapter.in.rest.dto.DtoTaskResponse;
import com.ejemplo.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

  private final CreateTaskUseCase createTaskUseCase;
  private final DeleteTaskUseCase deleteTaskUseCase;
  private final GetTaskUseCase getTaskUseCase;
  private final ListTaskUseCase listTaskUseCase;
  private final TaskRestMapper taskRestMapper;
  private final UpdateTaskUseCase updateTaskUseCase;
  private final UploadTaskImageUseCase uploadTaskImageUseCase;

  @PostMapping
  public ResponseEntity<DtoTaskResponse> create(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
    Task task = taskRestMapper.toDomain(createTaskRequest);
    Task saved = createTaskUseCase.create(task);
    return ResponseEntity.status(HttpStatus.CREATED).body(taskRestMapper.toResponse(saved));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DtoTaskResponse> getById(@PathVariable long id) {
    Task got = getTaskUseCase.getById(id);
    return ResponseEntity.ok(taskRestMapper.toResponse(got));
  }

  @GetMapping
  public ResponseEntity<List<DtoTaskResponse>> getAll() {
    List<DtoTaskResponse> tasks = listTaskUseCase.findAll().stream().map(taskRestMapper::toResponse).toList();
    return ResponseEntity.ok(tasks);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    deleteTaskUseCase.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<DtoTaskResponse> update(@PathVariable long id, @Valid @RequestBody UpdateTaskRequest updateTaskRequest) {
    Task task = taskRestMapper.toDomain(updateTaskRequest);
    Task updated = updateTaskUseCase.update(id, task);
    return ResponseEntity.ok(taskRestMapper.toResponse(updated));
  }

  @PostMapping("/{id}/image")
  public ResponseEntity<DtoTaskResponse> uploadImage(@PathVariable long id, @RequestParam("image") MultipartFile image) throws IOException {
    Task task = uploadTaskImageUseCase.uploadImage(id, image.getOriginalFilename(), image.getBytes());
    return ResponseEntity.ok(taskRestMapper.toResponse(task));
  }

}