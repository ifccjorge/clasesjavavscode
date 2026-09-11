package com.ejemplo.application.port.out;

import java.util.List;
import java.util.Optional;

import com.ejemplo.domain.model.Task;

public interface TaskRepositoryPort {
  Task save(Task task);
  Optional<Task> findById(long id);
  List<Task> findAll();
}
