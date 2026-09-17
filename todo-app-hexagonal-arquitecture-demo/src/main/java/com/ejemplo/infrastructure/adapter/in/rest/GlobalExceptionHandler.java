package com.ejemplo.infrastructure.adapter.in.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ejemplo.domain.exception.TaskNotFoundException;
import com.ejemplo.domain.model.TaskStatus;

import tools.jackson.databind.exc.InvalidFormatException;

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
    problem.setProperty("Errores", errors);
    return problem;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ProblemDetail handleUnreadableBody(HttpMessageNotReadableException ex) {
    String detail = "El cuerpo de la petición no es válido";
    if (ex.getCause() instanceof InvalidFormatException ife 
        && ife.getTargetType() == TaskStatus.class) {
            detail = "el status '" + ife.getValue() + "' no es válido. " + "Valores permitidos: " + Arrays.toString(TaskStatus.values());
    }
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    problem.setTitle("Dato inválido");
    return problem;
  }

}
