package com.ejemplo.application.port.in;

import com.ejemplo.domain.model.Task;

public interface GetTaskUseCase {
  Task getById(long id);
}
