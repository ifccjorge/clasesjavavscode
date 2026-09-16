package com.ejemplo.application.port.in;

import com.ejemplo.domain.model.Task;

public interface UploadTaskImageUseCase {
  Task uploadImage(long id, String fileName, byte[] content);
}
