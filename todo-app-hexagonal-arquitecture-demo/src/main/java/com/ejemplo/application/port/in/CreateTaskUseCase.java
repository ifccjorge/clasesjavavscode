package com.ejemplo.application.port.in;

import com.ejemplo.domain.model.Task;

public interface CreateTaskUseCase {
	Task create(Task task);
}
