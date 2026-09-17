package com.ejemplo.domain.model;

import java.time.LocalDateTime;

public record Task(
    long id,
    String title,
    String description,
    TaskStatus status,
    LocalDateTime createdAt,
    LocalDateTime completedAt,
    String imagePath) {

  // El constructor sustituye a initDefault
  public Task {
    if (status == null)
      status = TaskStatus.PENDING;
    if (createdAt == null)
      createdAt = LocalDateTime.now();
  }

  public Task complete() {
    if (status == TaskStatus.COMPLETED)
      throw new IllegalStateException("la tarea ya estaba completa");
    return new Task(id, title, description, TaskStatus.COMPLETED, createdAt, LocalDateTime.now(), imagePath);
  }

  public Task reopen() {
    if (status == TaskStatus.PENDING)
      throw new IllegalStateException("la tarea ya estaba pendiente");
    return new Task(id, title, description, TaskStatus.PENDING, createdAt, null, imagePath);
  }

  public Task changeStatusTo(TaskStatus newStatus) {
    if (newStatus == null || newStatus == status)
      return this;
    return newStatus == TaskStatus.COMPLETED ? complete() : reopen();
  }

  public Task update(String title, String description) {
    return new Task(id, title, description, status, createdAt, completedAt, imagePath);
  }

  public Task attachImage(String imagePath) {
    return new Task(id, title, description, status, createdAt, completedAt, imagePath);
  }

}
