package com.ejemplo.application.port.in;

import com.ejemplo.domain.model.Task;

public interface UpdateTaskUseCase {
  Task update(long id, Task task);
}
