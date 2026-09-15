package com.ejemplo.application.port.in;

import com.ejemplo.domain.model.Task;

public interface DeleteTaskUseCase {
	Task delete(long id);
}
