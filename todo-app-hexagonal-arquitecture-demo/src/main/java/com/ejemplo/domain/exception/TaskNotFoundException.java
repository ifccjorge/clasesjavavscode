package com.ejemplo.domain.exception;

public class TaskNotFoundException extends RuntimeException {
  //private static final long serialVersionUID = 1L;
  public TaskNotFoundException(long id) {
    super("No ha sido encontrado la tarea con id: " + id);
  }
}
