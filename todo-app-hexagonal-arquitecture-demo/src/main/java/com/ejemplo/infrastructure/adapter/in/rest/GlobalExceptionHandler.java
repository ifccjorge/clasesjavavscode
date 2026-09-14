package com.ejemplo.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ejemplo.domain.exception.TaskNotFoundException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
  @ExceptionHandler(TaskNotFoundException.class)
  public ProblemDetail handleTaskNotFoundException(TaskNotFoundException ex) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problem.setTitle("Tarea no encontrada");
    return problem;
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidationError(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
      .map(error -> error.getField() + ": " + error.getDefaultMessage())
      .toList();
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validación");
    problem.setTitle("Datos inválidos recibidos");
    problem.setProperty("errors", errors);
    return problem;
  }
}
