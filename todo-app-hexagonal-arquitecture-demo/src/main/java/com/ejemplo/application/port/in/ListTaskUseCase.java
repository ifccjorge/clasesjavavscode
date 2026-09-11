package com.ejemplo.application.port.in;

import java.util.List;

import com.ejemplo.domain.model.Task;

public interface ListTaskUseCase {
  List<Task> findAll();
}
